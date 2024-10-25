package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.page.MainPage;

public class SectionConstructorTest {
    WebDriver driver;
    private MainPage mainPage;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        driver = driverRule.getDriver();

        mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.waitMainPageLoad();
    }


    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void navigateToBunSection() {
        mainPage.clickOnFillingButton();
        mainPage.clickOnBunButton();
        mainPage.sectionBunTest();
    }

    @Test
    @DisplayName("Переход к разделу «Соусы")
    public void navigateToSauceSection() {
        mainPage.clickOnSauceButton();
        mainPage.sectionSauceTest();
    }

    @Test
    @DisplayName("Переход к разделу «Начинки")
    public void navigateToFillingSection() {
        mainPage.clickOnFillingButton();
        mainPage.sectionFillingTest();
    }

}