package tests;

import base.BaseTest;
import listeners.ReportListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ConfigReader;

@Listeners(ReportListener.class)
public class HomePageTest  extends BaseTest {

    @Test
    public void verifyIsLogoutButtonVisible(){

        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        assertUtil.assertVisible(ConfigReader.getLocator("login.btnLogin"));
        loginPage.clickLogin();
        testUtil.waitForElementVisible(ConfigReader.getLocator("home.btnLogout"));
        assertUtil.assertVisible(ConfigReader.getLocator("home.btnLogout"));
    }

    @Test
    public void verifyLoginPageURLAfterRedirection(){

        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickLogin();
        homePage.clickLogoutButton();
        assertUtil.assertUrlContains(page,"https://the-internet.herokuapp.com/login");
    }

}
