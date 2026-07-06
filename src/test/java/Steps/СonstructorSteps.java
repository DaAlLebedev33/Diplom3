package Steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class СonstructorSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    // Кнопка выбора булки
    private final By buttonBuns = By.xpath("//p[text()='Краторная булка N-200i']");

    // Кнопка соусов
    private final By buttonSauce = By.xpath("//p[text()='Соус с шипами Антарианского плоскоходца']");

    // Кнопка начинки
    private final By buttonFilling = By.xpath("//p[text()='Хрустящие минеральные кольца']");

    // Текст при открытии ингридиента
    private final By textOpenIngridient = By.xpath("//h2[text()='Детали ингредиента']");

    public СonstructorSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Скролл до элемента")
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
        try {
            Thread.sleep(500); // Небольшая задержка для анимации скролла
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

        @Step("Скролл до элемента и клик по кнопке 'Соусы'")
        public void clickSauceButton () {
            WebElement sauceTag = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonSauce));
            scrollToElement(sauceTag);
            wait.until(ExpectedConditions.elementToBeClickable(sauceTag)).click();
            Assertions.assertTrue(driver.findElement(textOpenIngridient).getText()
                    .equals("Детали ингредиента"));
        }

    @Step("Скролл до элемента и клик по кнопке 'Начинки'")
    public void clickFillingButton() {
        WebElement fillingTag = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonFilling));
        scrollToElement(fillingTag);
        wait.until(ExpectedConditions.elementToBeClickable(fillingTag)).click();
        Assertions.assertTrue(driver.findElement(textOpenIngridient).getText()
                .equals("Детали ингредиента"));
    }

    @Step("Клик по кнопке 'Булки'")
    public void clickBunsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonBuns)).click();
        Assertions.assertTrue(driver.findElement(textOpenIngridient).getText()
                .equals("Детали ингредиента"));
    }
}
