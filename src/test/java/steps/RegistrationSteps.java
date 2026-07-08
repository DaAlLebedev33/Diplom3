package steps;

import helpers.Browser;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.Url.REGISTRATION_USER;

public class RegistrationSteps {
    private Browser browser;
    private WebDriver driver;
    private WebDriverWait wait;

    // Поле "Имя"
    private final By inputRegistrationName = By.xpath("//input[@name='name']");

    // Поле "Email"
    private final By inputRegistrationEmail = By.xpath("//label[text()='Email']/following::input");

    // Поле "Пароль"
    private final By inputRegistrationPassword = By.xpath("//input[@type='password']");

    // Кнопка "Зарегистрироваться"
    private final By buttonRegistration = By.xpath("//button[text()='Зарегистрироваться']");

    // Текст "Некорректный пароль"
    private final By textIncorrectedPassword = By.xpath("//p[text()='Некорректный пароль']");

    // Проверка текста при успешной регистрации
    private final By textAuthorization = By.xpath("//h2[text()='Вход']");



    public RegistrationSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввод имени")
    public void inputName(String name) {
        driver.findElement(inputRegistrationName);
        driver.findElement(inputRegistrationName).sendKeys(name);
    }

    @Step("Ввод Email")
    public void inputEmail(String email) {
        driver.findElement(inputRegistrationEmail);
        driver.findElement(inputRegistrationEmail).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void inputPassword(String password) {
        driver.findElement(inputRegistrationPassword);
        driver.findElement(inputRegistrationPassword).sendKeys(password);
    }

    @Step("Нажать кнопку Зарегистрироваться")
    public void clickButtonRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonRegistration));
        driver.findElement(buttonRegistration).click();
    }

    @Step("Текст некорректного пароля")
    public void textIncorrectPassError() {
        Assertions.assertTrue(driver.findElement(textIncorrectedPassword).getText()
                .equals("Некорректный пароль"));
    }

    @Step("Текст Вход при успешной регистрации")
    public void textEntranceSucsses() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(textAuthorization));
    }

    @Step("Метод регистрации")
    public void fullRegistration(String name, String email, String password) {
        browser = new Browser();
        browser.setUp("Chrome", REGISTRATION_USER);
        this.driver = browser.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        inputName(name);
        inputEmail(email);
        inputPassword(password);
        clickButtonRegistration();
        textEntranceSucsses();

        browser.tearDown();
    }
}
