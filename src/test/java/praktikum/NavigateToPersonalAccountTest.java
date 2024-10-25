package praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.config.EnvConfig;
import praktikum.page.MainPage;
import praktikum.page.RegisterPage;

public class NavigateToPersonalAccountTest {
    WebDriver driver;
    private MainPage mainPage;
    private RegisterPage registerPage;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        driver = driverRule.getDriver();
        RestAssured.baseURI = EnvConfig.BASE_URL;

        mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);

        mainPage.open();
        mainPage.waitMainPageLoad();
    }


    @Test
    @DisplayName("Переход в личный кабинет по клику на «Личный кабинет»")
    public void navigateToPersonalAccountByClickOnButtonPersonalAccount() {
        mainPage.clickOnPersonalAccountButton();
        registerPage.isEnterTextVisible();
    }
}