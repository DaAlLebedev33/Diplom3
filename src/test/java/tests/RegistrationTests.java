package tests;

import helpers.Browser;
import steps.DeleteUserSteps;
import steps.RandomDataForCreateUser;
import steps.RegistrationSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static constants.Url.REGISTRATION_USER;

public class RegistrationTests {
    private Browser browser;
    private RegistrationSteps step;
    private DeleteUserSteps deleteUserSteps = new DeleteUserSteps();
    private String createdUserEmail;
    private String createdUserPassword;

    static Stream<Object[]> registrationData() {
        return Stream.of(
                new Object[]{"Chrome", RandomDataForCreateUser.randomName(), RandomDataForCreateUser.randomEmail(), RandomDataForCreateUser.randomPassword(8)},
                new Object[]{"Chrome", RandomDataForCreateUser.randomName(), RandomDataForCreateUser.randomEmail(), RandomDataForCreateUser.randomPassword(5)},
                new Object[]{"Yandex", RandomDataForCreateUser.randomName(), RandomDataForCreateUser.randomEmail(), RandomDataForCreateUser.randomPassword(8)},
                new Object[]{"Yandex", RandomDataForCreateUser.randomName(), RandomDataForCreateUser.randomEmail(), RandomDataForCreateUser.randomPassword(5)}
        );
    }


    @ParameterizedTest
    @MethodSource("registrationData")
    @DisplayName("Успешная регистрация")
    public void positiveRegistration(String browserName, String name, String email, String password){
        createdUserEmail = email;
        createdUserPassword = password;

        browser = new Browser();
        browser.setUp(browserName, REGISTRATION_USER);

        step = new RegistrationSteps(browser.driver);

        step.inputName(name);
        step.inputEmail(email);
        step.inputPassword(password);
        step.clickButtonRegistration();

        if (password.length() >= 6) {
            step.textEntranceSucsses();
        } else {
            step.textIncorrectPassError();
        }
    }

    @AfterEach
    public void tearDown(){
        deleteUserSteps.loginAndDeleteUser(createdUserEmail, createdUserPassword);
        browser.tearDown();
    }
}
