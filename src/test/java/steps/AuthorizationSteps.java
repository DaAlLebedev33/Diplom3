package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationSteps {
    private WebDriver driver;
    private WebDriverWait wait;


    // Кнопка "Вход" в окне регистрации
    private final By buttonAuthForRegistration = By.xpath("//a[@href='/login']");

    // Кнопка "Войти в аккаунт" на главной странице
    private final By buttonLoginToMainPage = By.xpath("//button[text()='Войти в аккаунт']");

    // Вход через личный кабинет
    private final By buttonMainCabinet = By.xpath("//p[text()='Личный Кабинет']");

    // Вход через Восстановление пароля
    private final By buttonRecoveryPassword = By.xpath("//a[text()='Восстановить пароль']");

    // Кнопка Войти в окне Восстановление пароля
    private final By buttonLoginForRecoveryPassword = By.xpath("//a[text()='Войти']");

    // Поле "Email"
    private final By inputRegistrationEmail = By.xpath("//input[@name='name']");

    // Поле "Пароль"
    private final By inputRegistrationPassword = By.xpath("//input[@type='password']");

    // Кнопка "Войти"
    private final By buttonLoginToPageAuthorization = By.xpath("//button[text()='Войти']");

    // Проверка текста при успешном входе
    private final By textAuthorization = By.xpath("//h1[text()='Соберите бургер']");

    // Кнопка выйти
    private final By buttonExitForUser = By.xpath("//button[text()='Выход']");

    public AuthorizationSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void performLogin(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputRegistrationEmail)).sendKeys(email);
        driver.findElement(inputRegistrationPassword).sendKeys(password);
        driver.findElement(buttonLoginToPageAuthorization).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(textAuthorization));
    }

    @Step("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginMainPageButton(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(buttonLoginToMainPage)).click();
        performLogin(email, password);
    }

    @Step("Вход через кнопку «Личный кабинет»")
    public void loginPersonalCabinet(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(buttonMainCabinet)).click();
        performLogin(email, password);
    }

    @Step("Вход через кнопку в форме регистрации")
    public void loginRegistrationForm(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(buttonAuthForRegistration)).click();
        performLogin(email, password);
    }

    @Step("Вход через кнопку в форме восстановления пароля")
    public void loginRecoveryPasswordForm(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(buttonRecoveryPassword)).click();
        wait.until(ExpectedConditions.elementToBeClickable(buttonLoginForRecoveryPassword)).click();
        performLogin(email, password);
    }

    @Step("Выход из аккаунта")
    public void exitForAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonMainCabinet)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonExitForUser)).click();
    }

    @Step("Проверка Главной страинцы")
    public void checkMainPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(textAuthorization));
    }
}