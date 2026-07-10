package tests;

import dev.failsafe.internal.util.Assert;
import helpers.Browser;
import steps.AuthorizationSteps;
import steps.ConstructorSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static constants.Url.BASE_URL;
import static java.awt.SystemColor.text;

public class ConstructorTests {
    private AuthorizationSteps authorizationSteps;
    private Browser browser;
    private ConstructorSteps constructor;

    static Stream<Object[]> constructorTest() {
        return Stream.of(
                new Object[]{"Chrome"},
                new Object[]{"Yandex"}
        );
    }

    @ParameterizedTest
    @MethodSource("constructorTest")
    @DisplayName("Проверка открытия раздела Булки")
    public void checkCunstructorBuns(String browserName){
        browser = new Browser();
        browser.setUp(browserName, BASE_URL);

        authorizationSteps = new AuthorizationSteps(browser.driver);
        constructor = new ConstructorSteps(browser.driver);
        constructor.verifyIngredientsContainerVisible();
        constructor.switchBunTab();
    }

    @ParameterizedTest
    @MethodSource("constructorTest")
    @DisplayName("Проверка открытия раздела Соусы")
    public void checkCunstructorSauce(String browserName){
        browser = new Browser();
        browser.setUp(browserName, BASE_URL);

        authorizationSteps = new AuthorizationSteps(browser.driver);
        constructor = new ConstructorSteps(browser.driver);
        constructor.verifyIngredientsContainerVisible();
        constructor.switchSaucesTab();
    }

    @ParameterizedTest
    @MethodSource("constructorTest")
    @DisplayName("Проверка открытия раздела Начинки")
    public void checkCunstructorFilling(String browserName){
        browser = new Browser();
        browser.setUp(browserName, BASE_URL);

        authorizationSteps = new AuthorizationSteps(browser.driver);
        constructor = new ConstructorSteps(browser.driver);
        constructor.verifyIngredientsContainerVisible();
        constructor.switchFillingTab();
    }

    @AfterEach
    public void tearDown(){
        browser.tearDown();
    }
}
