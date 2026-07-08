package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransitionPersonCabinetSteps {
    private WebDriverWait wait;

    // Кнопка "Конструктор"
    private final By buttonConstructor = By.xpath("//p[text()='Конструктор']");

    // Логотип Stellar Burgers
    private final By buttonLogoStellarBurger = By.xpath("//*[local-name()='svg' and @width='290' and @height='50']");


    public TransitionPersonCabinetSteps(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Step("Переход через кнопку Конструктор")
    public void transitionConstructor() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonConstructor)).click();
    }

    @Step("Переход через логотип")
    public void transitionLogoStellarBurger() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogoStellarBurger)).click();
    }
}
