package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.api.APIChecks;
import praktikum.page.AuthPage;
import praktikum.page.MainPage;
import praktikum.page.RegisterPage;



public class RegistrationTest {
    WebDriver driver;
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

        mainPage = new MainPage(driver);
        authPage = new AuthPage(driver);
        registerPage = new RegisterPage(driver);
        apiChecks = new APIChecks();

        mainPage.open();
        mainPage.waitMainPageLoad();
        mainPage.clickOnPersonalAccountButton();

        authPage.waitAuthPageLoad();
        authPage.clickOnRegisterLink();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            apiChecks.deleteUser(accessToken);
        }
    }


    @Test
    @DisplayName("Успешная регистрация")
    public void registerTest() {

        registerPage.waitRegisterPageLoad();
        registerPage.fillRegisterFields("TestDiplom", "TestDiplom23@mail.ru", "TestDiplom");
        registerPage.clickOnRegisterButton();
        registerPage.isEnterTextVisible();

        authPage.fillAuthFields("TestDiplom23@mail.ru", "TestDiplom");

        accessToken = authPage.fetchAuthTokenFromLocalStorage();
    }


    @Test
    @DisplayName("Ошибку для некорректного пароля")
    public void registerIncorrectPasswordTest() {

        registerPage.waitRegisterPageLoad();
        registerPage.fillRegisterFields("TestDiplom", "TestDiplom24@mail.ru", "Test");
        registerPage.clickOnRegisterButton();
        registerPage.incorrectPassword();
    }
}