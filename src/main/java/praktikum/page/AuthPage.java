package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static praktikum.config.EnvConfig.EXPLICIT_WAIT;

public class AuthPage {
    private WebDriver driver;

    private By emailField = By.cssSelector(".Auth_fieldset__1QzWN:nth-child(1) input.text_type_main-default");
    private By passwordField = By.cssSelector(".Auth_fieldset__1QzWN:nth-child(2) input.text_type_main-default");
    private By enterButton = By.xpath(".//button[text()='Войти']");
    private By registerLink = By.xpath(".//a[text() = 'Зарегистрироваться']");
    private By recoverPasswordLink = By.xpath(".//a[text() = 'Восстановить пароль']");
    private By enterLinkInPasswordRecoveryForm = By.xpath(".//a[text()='Войти']");

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Ждем, когда страница с авторизацией загрузится")
    public void waitAuthPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(enterButton));
    }

    @Step("Нажимаем на ссылку Зарегистрироваться")
    public void clickOnRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Заполняем поле email")
    public void fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполняем поле пароль")
    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажимаем на кнопку Войти")
    public void clickOnEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Заполняем всю форму для авторизации")
    public void fillAuthFields(String email, String password) {
        fillEmailField(email);
        fillPasswordField(password);
        clickOnEnterButton();
    }

    @Step("Нажимаем на ссылку Восстановить пароль")
    public void clickOnRecoverPasswordLink() {
        driver.findElement(recoverPasswordLink).click();
    }

    @Step("Нажимаем на ссылку Войти")
    public void clickOnEnterLinkInPasswordRecoveryForm() {
        driver.findElement(enterLinkInPasswordRecoveryForm).click();
    }

    @Step("Достаем токен из LocalStorage")
    public String fetchAuthTokenFromLocalStorage() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector("a[class^='BurgerIngredient']"), 2));

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        String accessToken = localStorage.getItem("accessToken");

        return accessToken;
    }
}