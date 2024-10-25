package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.api.APIChecks;
import praktikum.api.Auth;
import praktikum.api.User;
import praktikum.page.AuthPage;
import praktikum.page.MainPage;
import praktikum.page.RegisterPage;

public class AuthorizationTest {

    WebDriver driver;
    private User user;
    private MainPage mainPage;
    private RegisterPage registerPage;
    private AuthPage authPage;
    private APIChecks apiChecks;
    private String accessToken;


    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        driver = driverRule.getDriver();

        user = User.randomUser();
        apiChecks = new APIChecks();

        apiChecks.createUser(user);

        Auth loginData = new Auth(user.getEmail(), user.getPassword());
        accessToken = apiChecks.logIn(loginData).extract().path("accessToken");

        mainPage = new MainPage(driver);
        authPage = new AuthPage(driver);
        registerPage = new RegisterPage(driver);

        mainPage.open();
        mainPage.waitMainPageLoad();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            apiChecks.deleteUser(accessToken);
        }
    }


    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void authFromEnterToAccountButton() {
        mainPage.clickOnEnterToAccountButton();
        authPage.fillAuthFields(user.getEmail(), user.getPassword());
        mainPage.placeAnOrderButtonVisibility();
    }

    @Test
    @DisplayName("Вход по кнопке «Личный Кабинет» на главной странице")
    public void authFromPersonalAccountButton() {
        mainPage.clickOnPersonalAccountButton();
        authPage.fillAuthFields(user.getEmail(), user.getPassword());
        mainPage.placeAnOrderButtonVisibility();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void authFromButtonInRegistrationForm() {
        mainPage.clickOnEnterToAccountButton();
        authPage.waitAuthPageLoad();
        authPage.clickOnRegisterLink();
        registerPage.waitRegisterPageLoad();
        registerPage.clickOnEnterLink();
        authPage.fillAuthFields(user.getEmail(), user.getPassword());
        mainPage.placeAnOrderButtonVisibility();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void authFromButtonInPasswordRecoveryForm() {
        mainPage.clickOnEnterToAccountButton();
        authPage.waitAuthPageLoad();
        authPage.clickOnRecoverPasswordLink();
        authPage.clickOnEnterLinkInPasswordRecoveryForm();
        authPage.fillAuthFields(user.getEmail(), user.getPassword());
        mainPage.placeAnOrderButtonVisibility();
    }
}