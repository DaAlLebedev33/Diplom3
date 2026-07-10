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

    // Меню конкретной вкладки
    private final By menuForSpecificTab = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");

    // Контейнер со всеми ингредиентами
    private final By ingredientsContainer = By.xpath("//section[contains(@class, 'BurgerIngredients_ingredients__1N8v2')]");

    // Вкладки по ингридиентам
    private final By tabBun = By.xpath(".//span[text()='Булки']/parent::div");
    private final By tabSauces = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By tabFilling = By.xpath(".//span[text()='Начинки']");


    public ConstructorSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        driver.findElement(tabFilling).click();
    }

    @Step("Проверка, что заголовок 'Начинки' отображается")
    public String verifyAllTitleVisible() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(menuForSpecificTab));
        Assertions.assertTrue(title.isDisplayed(),
                "Заголовок активной вкладки отображается");
        return title.getText();
    }

    @Step("Переключение на вкладку 'Булки' и проверка заголовка")
    public void switchBunTab() {
        clickBunsTab();
        String actualTitle = verifyAllTitleVisible();
        Assertions.assertEquals("Булки", actualTitle,
                "Заголовок активной вкладки должен быть 'Начинки'");
    }

    @Step("Переключение на вкладку 'Соусы' и проверка заголовка")
    public void switchSaucesTab() {
        clickSaucesTab();
        String actualTitle = verifyAllTitleVisible();
        Assertions.assertEquals("Соусы", actualTitle,
                "Заголовок активной вкладки должен быть 'Начинки'");
    }

    @Step("Переключение на вкладку 'Начинки' и проверка заголовка")
    public void switchFillingTab() {
        clickFillingsTab();
        String actualTitle = verifyAllTitleVisible();
        Assertions.assertEquals("Начинки", actualTitle,
                "Заголовок активной вкладки должен быть 'Начинки'");
    }
}
