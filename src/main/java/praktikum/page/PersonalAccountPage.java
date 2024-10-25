package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static praktikum.config.EnvConfig.EXPLICIT_WAIT;

public class PersonalAccountPage {
    private WebDriver driver;

    private By logOutButton = By.xpath(".//button[text()='Выход']");
    private By enterText = By.xpath(".//h2[text()='Вход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Ждем, когда кнопка Выход будет видна")
    public void personalAccountPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
    }

    @Step("Нажимаем на кнопку Выход")
    public void clickOnLogOutButton() {
        driver.findElement(logOutButton).click();
    }

    @Step("Переход на страницу авторизации при успешном выходе")
    public boolean isLogOutSuccessful() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(enterText));
        return driver.findElement(enterText).isDisplayed();
    }
}