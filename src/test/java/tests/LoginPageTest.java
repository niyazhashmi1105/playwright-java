package tests;

import base.BaseTest;
import listeners.ReportListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ConfigReader;

@Listeners(ReportListener.class)
public class LoginPageTest extends BaseTest {


    @Test(priority= 1)
    public void verifyPageTitleOnSuccessfulLogin(){
        assertUtil.assertVisible(ConfigReader.getLocator("login.username"));
        assertUtil.assertVisible(ConfigReader.getLocator("login.password"));
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        assertUtil.assertEnabled(ConfigReader.getLocator("login.btnLogin"));
        loginPage.clickLogin();
        assertUtil.assertTextContains(ConfigReader.getLocator("home.loggedInText"),"You logged into a secure area!");

    }

    @Test(priority= 2)
    public void verifyInvalidPageTitleOnSuccessfulLogin(){

        assertUtil.assertEditable(ConfigReader.getLocator("login.username"));
        assertUtil.assertEditable(ConfigReader.getLocator("login.password"));
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickLogin();
        assertUtil.assertPageTitleContains(page,"Internet");
    }

}
