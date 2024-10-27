package tests;

import base.BaseTest;
import listeners.ReportListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import configurator.ConfigReader;

@Listeners(ReportListener.class)
public class HomePageTest  extends BaseTest {

    @Test(priority=1)
    public void verifyIsLogoutButtonVisible(){

        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        assertUtil.assertVisible(ConfigReader.getLoginBtn());
        loginPage.clickLogin();
        testUtil.waitForElementVisible(ConfigReader.getLogoutBtn());
        assertUtil.assertVisible(ConfigReader.getLogoutBtn());
    }

    @Test(priority=2)
    public void verifyLoginPageURLAfterRedirection(){

        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickLogin();
        homePage.clickLogoutButton();
        assertUtil.assertUrlContains(page,"https://the-internet.herokuapp.com/login");
    }

}
