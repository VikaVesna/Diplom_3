package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static praktikum.config.EnvConfig.EXPLICIT_WAIT;

public class RegisterPage {
    private WebDriver driver;

    private By nameField = By.cssSelector(".Auth_fieldset__1QzWN:nth-child(1) input.text_type_main-default");
    private By emailField = By.cssSelector(".Auth_fieldset__1QzWN:nth-child(2) input.text_type_main-default");
    private By passwordField = By.cssSelector(".Auth_fieldset__1QzWN:nth-child(3) input.text_type_main-default");
    private By registerButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private By enterText = By.xpath(".//h2[text()='Вход']");
    private By incorrectPasswordTest = By.xpath(".//p[text()='Некорректный пароль']");
    private By enterLink = By.xpath(".//a[text()='Войти']");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ждем, когда страница с регистрацией загрузится")
    public void waitRegisterPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(registerButton));
    }

    @Step("Заполняем поле имя")
    public void fillNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Заполняем поле email")
    public void fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполняем поле пароль")
    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажимаем на кнопку Зарегистрироваться")
    public void clickOnRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Нажимаем на ссылку Войти")
    public void clickOnEnterLink() {
        driver.findElement(enterLink).click();
    }

    @Step("Заполняем всю форму регистрации")
    public void fillRegisterFields(String name, String email, String password) {
        fillNameField(name);
        fillEmailField(email);
        fillPasswordField(password);
        clickOnRegisterButton();
    }

    @Step("Проверка видимости текста Вход")
    public boolean isEnterTextVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(enterText));
        return driver.findElement(enterText).isDisplayed();
    }

    @Step("Проверка появления ошибки при некорректном пароле")
    public boolean incorrectPassword() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordTest));
        return driver.findElement(incorrectPasswordTest).isDisplayed();
    }
}