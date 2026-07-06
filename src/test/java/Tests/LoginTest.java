package Tests;

import Helpers.Browser;
import Steps.AuthorizationSteps;
import Steps.RandomDataForCreateUser;
import Steps.RegistrationSteps;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static constants.Url.*;

@ExtendWith(AllureJunit5.class)
public class LoginTest {
    private Browser browser;
    private RegistrationSteps step;
    private AuthorizationSteps authorizationSteps;
    private String email;
    private String password;
    private String name;

    @BeforeEach
    public void setUp() {
        name = RandomDataForCreateUser.randomName();
        email = RandomDataForCreateUser.randomEmail();
        password = RandomDataForCreateUser.randomPassword(8);

        step = new RegistrationSteps(null);
        step.fullRegistration(name, email, password);
    }

    static Stream<Object[]> loginDataOne() {
        return Stream.of(
                new Object[]{"Chrome"},
                new Object[]{"Yandex"}
        );
    }

    @ParameterizedTest
    @MethodSource("loginDataOne")
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void testLoginViaMainPageButton(String browserName) {
        browser = new Browser();
        browser.setUp(browserName, BASE_URL);

        authorizationSteps = new AuthorizationSteps(browser.driver);

        authorizationSteps.loginMainPageButton(email, password);

        authorizationSteps.exitForAccount();
    }

    @ParameterizedTest
    @MethodSource("loginDataOne")
    @DisplayName("Вход по кнопке «Вход» через личный кабинет")
    public void testLoginPersonalCabinet(String browserName) {
        browser = new Browser();
        browser.setUp(browserName, BASE_URL);

        authorizationSteps = new AuthorizationSteps(browser.driver);

        authorizationSteps.loginPersonalCabinet(email, password);

        authorizationSteps.exitForAccount();
    }

    @ParameterizedTest
    @MethodSource("loginDataOne")
    @DisplayName("Вход по кнопке «Вход» в окне регистрации")
    public void testLoginPageRegistration(String browserName) {
        browser = new Browser();
        browser.setUp(browserName, REGISTRATION_USER);

        authorizationSteps = new AuthorizationSteps(browser.driver);

        authorizationSteps.loginRegistrationForm(email, password);

        authorizationSteps.exitForAccount();
    }

    @ParameterizedTest
    @MethodSource("loginDataOne")
    @DisplayName("Вход по кнопке «Вход» через Восстановление пароля")
    public void testLoginRecoveryPassword(String browserName) {
        browser = new Browser();
        browser.setUp(browserName, URL_LOGIN);

        authorizationSteps = new AuthorizationSteps(browser.driver);

        authorizationSteps.loginRecoveryPasswordForm(email, password);

        authorizationSteps.exitForAccount();
    }

    @AfterEach
    public void tearDown(){
        browser.tearDown();
    }
}
