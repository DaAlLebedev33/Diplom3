package steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    // Заголовки секций
    private final By bunsTitle = By.xpath("//h2[text()='Булки']");
    private final By saucesTitle = By.xpath("//h2[text()='Соусы']");
    private final By fillingsTitle = By.xpath("//h2[text()='Начинки']");

    // Контейнер со всеми ингредиентами
    private final By ingredientsContainer = By.xpath("//section[contains(@class, 'BurgerIngredients_ingredients__1N8v2')]");

    // Вкладки по ингридиентам
    private final By tabBun = By.xpath(".//span[text()='Булки']/parent::div");
    private final By tabSauces = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By tabFilling = By.xpath(".//span[text()='Начинки']/parent::div");


    public ConstructorSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Скролл до элемента")
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Проверка, что контейнер со всеми ингредиентами отображается")
    public void verifyIngredientsContainerVisible() {
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(ingredientsContainer));
        Assertions.assertTrue(container.isDisplayed(),
                "Контейнер со всеми ингредиентами должен отображаться");
    }

    @Step("Клик по вкладке 'Булки'")
    public void clickBunsTab() {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(tabBun));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
    }

    @Step("Клик по вкладке 'Соусы'")
    public void clickSaucesTab() {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(tabSauces));
        tab.click();
    }

    @Step("Клик по вкладке 'Начинки'")
    public void clickFillingsTab() {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(tabFilling));
        tab.click();
    }

    @Step("Проверка, что заголовок 'Булки' отображается")
    public void verifyBunTitleVisible() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTitle));
        Assertions.assertTrue(title.isDisplayed(),
                "Заголовок 'Булки' должен отображаться");
    }

    @Step("Проверка, что заголовок 'Соусы' отображается")
    public void verifySaucesTitleVisible() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTitle));
        Assertions.assertTrue(title.isDisplayed(),
                "Заголовок 'Соусы' должен отображаться");
    }

    @Step("Проверка, что заголовок 'Начинки' отображается")
    public void verifyFillingTitleVisible() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTitle));
        Assertions.assertTrue(title.isDisplayed(),
                "Заголовок 'Начинки' должен отображаться");
    }

    // Комбинированные методы для полного сценария
    @Step("Переключение на вкладку 'Булки' и проверка заголовка")
    public void switchBunTab() {
        clickBunsTab();
        verifyBunTitleVisible();
    }

    @Step("Переключение на вкладку 'Соусы' и проверка заголовка")
    public void switchSaucesTab() {
        clickSaucesTab();
        verifySaucesTitleVisible();
    }

    @Step("Переключение на вкладку 'Начинки' и проверка заголовка")
    public void switchFillingTab() {
        clickFillingsTab();
        verifyFillingTitleVisible();
    }

}
