package tests;

import helpers.Browser;
import steps.AuthorizationSteps;
import steps.TransitionPersonCabinetSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static constants.Url.URL_LOGIN;

public class TransitionTests {
    private AuthorizationSteps authorizationSteps;
    private Browser browser;
    private TransitionPersonCabinetSteps transition;


    static Stream<Object[]> transitionTest() {
        return Stream.of(
                new Object[]{"Chrome"},
                new Object[]{"Yandex"}
        );
    }

    @ParameterizedTest
    @MethodSource("transitionTest")
    @DisplayName("Проверка перехода через конструктор")
    public void checkTransitionButtomCunstructor(String browserName){
        browser = new Browser();
        browser.setUp(browserName, URL_LOGIN);

        authorizationSteps = new AuthorizationSteps(browser.driver);
        transition = new TransitionPersonCabinetSteps(browser.driver);
        transition.transitionConstructor();
        authorizationSteps.checkMainPage();
    }

    @ParameterizedTest
    @MethodSource("transitionTest")
    @DisplayName("Проверка перехода через логотип")
    public void checkTransitionLogo(String browserName){
        browser = new Browser();
        browser.setUp(browserName, URL_LOGIN);

        authorizationSteps = new AuthorizationSteps(browser.driver);
        transition = new TransitionPersonCabinetSteps(browser.driver);
        transition.transitionLogoStellarBurger();
        authorizationSteps.checkMainPage();
    }

    @AfterEach
    public void tearDown(){
        browser.tearDown();
    }
}
