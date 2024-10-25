package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.config.EnvConfig;

import java.time.Duration;

import static praktikum.config.EnvConfig.EXPLICIT_WAIT;
import static praktikum.config.EnvConfig.IMPLICIT_WAIT;


public class MainPage {
    private WebDriver driver;

    private By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private By enterToAccountButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    private By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private By logoButton = By.className("AppHeader_header__logo__2D0X2");
    private By collectBurgerText = By.xpath(".//h1[text()='Соберите бургер']");
    private By bunSection = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    private By sauceSection = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    private By fillingSection = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");
    private By placeAnOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем сайт")
    public void open() {
        driver.get(EnvConfig.BASE_URL);
    }

    @Step("Ждем загрузку главной страницы")
    public void waitMainPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(enterToAccountButton));
    }

    @Step("Нажимаем на кнопку Личный кабинет")
    public void clickOnPersonalAccountButton(){
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажимаем на кнопку Войти в аккаунт")
    public void clickOnEnterToAccountButton(){
        driver.findElement(enterToAccountButton).click();
    }

    @Step("Нажимаем на кнопку Конструктор")
    public void clickOnConstructorButton(){
        driver.findElement(constructorButton).click();
    }

    @Step("Нажимаем на логотип")
    public void clickOnLogoButton(){
        driver.findElement(logoButton).click();
    }

    @Step("проверяем видимость кнопки Оформить заказ")
    public void placeAnOrderButtonVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(placeAnOrderButton));
    }

    @Step("проверяем видимость текста Соберите бургер")
    public void CollectBurgerTextVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(collectBurgerText));
    }

    @Step("Нажимаем на кнопку Булки")
    public void clickOnBunButton(){
        driver.findElement(bunSection).click();
    }

    @Step("Нажимаем на кнопку Соусы")
    public void clickOnSauceButton(){
        driver.findElement(sauceSection).click();
    }

    @Step("Нажимаем на кнопку Начинки")
    public void clickOnFillingButton(){
        driver.findElement(fillingSection).click();
    }

    @Step("Проверяем переход к разделу Булки")
    public void sectionBunTest(){
        new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
                .until(ExpectedConditions.attributeContains(bunSection, "class", "current"));

    }

    @Step("Проверяем переход к разделу Соусы")
    public void sectionSauceTest(){
        new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
                .until(ExpectedConditions.attributeContains(sauceSection, "class", "current"));

    }

    @Step("Проверяем переход к разделу Начинки")
    public void sectionFillingTest(){
        new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
                .until(ExpectedConditions.attributeContains(fillingSection, "class", "current"));

    }

}