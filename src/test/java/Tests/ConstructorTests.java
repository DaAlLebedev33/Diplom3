package Tests;

import Helpers.Browser;
import Steps.AuthorizationSteps;
import Steps.СonstructorSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static constants.Url.BASE_URL;

public class ConstructorTests {
    private AuthorizationSteps authorizationSteps;
    private Browser browser;
    private СonstructorSteps constructor;

    static Stream<Object[]> constructorTest() {
        return Stream.of(
                new Object[]{"Chrome"},
                new Object[]{"Yandex"}
        );
    }

    @ParameterizedTest
    @MethodSource("constructorTest")
    @DisplayName("Проверка открытия окна Детали ингридиента для Булок")
    public void checkCunstructorBuns(String browserName){
        browser = new Browser();
        browser.setUp(browserName, BASE_URL);

        authorizationSteps = new AuthorizationSteps(browser.driver);
        constructor = new СonstructorSteps(browser.driver);
        constructor.clickBunsButton();
    }

    @ParameterizedTest
    @MethodSource("constructorTest")
    @DisplayName("Проверка открытия окна Детали ингридиента для Соусы")
    public void checkCunstructorSauce(String browserName){
        browser = new Browser();
        browser.setUp(browserName, BASE_URL);

        authorizationSteps = new AuthorizationSteps(browser.driver);
        constructor = new СonstructorSteps(browser.driver);
        constructor.clickSauceButton();
    }

    @ParameterizedTest
    @MethodSource("constructorTest")
    @DisplayName("Проверка открытия окна Детали ингридиента для Начинки")
    public void checkCunstructorFilling(String browserName){
        browser = new Browser();
        browser.setUp(browserName, BASE_URL);

        authorizationSteps = new AuthorizationSteps(browser.driver);
        constructor = new СonstructorSteps(browser.driver);
        constructor.clickFillingButton();
    }

    @AfterEach
    public void tearDown(){
        browser.tearDown();
    }
}
