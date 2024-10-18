package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.TestUtil;

//@Listeners(ReportListener.class)
public class HomePageTest  extends BaseTest {

    @Test
    public void verifyIsLogoutButtonVisible(){
        TestUtil testUtil = new TestUtil(page);
        LoginPage loginPage = new LoginPage(testUtil);
        HomePage homePage = new HomePage(testUtil);
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickLogin();
        Assert.assertTrue(homePage.isElementVisible());
    }

    @Test
    public void verifyLoginPageURLAfterRedirection(){
        TestUtil testUtil = new TestUtil(page);
        LoginPage loginPage = new LoginPage(testUtil);
        HomePage homePage = new HomePage(testUtil);
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickLogin();
        homePage.clickLogoutButton();
        String pageURL = testUtil.getPageURL();
        Assert.assertEquals(pageURL, "https://the-internet.herokuapp.com/login","Page URL is incorrect!");
    }

}
