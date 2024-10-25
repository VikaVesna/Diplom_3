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
import praktikum.page.PersonalAccountPage;


public class LogOutTest {
    WebDriver driver;
    private User user;
    private MainPage mainPage;
    private AuthPage authPage;
    private PersonalAccountPage personalAccountPage;
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
        personalAccountPage = new PersonalAccountPage(driver);

        mainPage.open();
        mainPage.waitMainPageLoad();
        mainPage.clickOnEnterToAccountButton();
        authPage.fillAuthFields(user.getEmail(), user.getPassword());
        mainPage.placeAnOrderButtonVisibility();
        mainPage.clickOnPersonalAccountButton();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            apiChecks.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void logoutButtonInPersonalAccountTest() {
        personalAccountPage.personalAccountPageLoad();
        personalAccountPage.clickOnLogOutButton();
        personalAccountPage.isLogOutSuccessful();
    }
}