package tests;

import base.BaseTest;
import listeners.ReportListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import configurator.ConfigReader;

@Listeners(ReportListener.class)
public class LoginPageTest extends BaseTest {


    @Test(priority= 1)
    public void verifyPageTitleOnSuccessfulLogin(){
        assertUtil.assertVisible(ConfigReader.getUsername());
        assertUtil.assertVisible(ConfigReader.getPassword());
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        assertUtil.assertEnabled(ConfigReader.getLoginBtn());
        loginPage.clickLogin();
        assertUtil.assertTextContains(ConfigReader.getHomePageText(),"You logged into a secure area!");

    }

    @Test(priority= 2)
    public void verifyInvalidPageTitleOnSuccessfulLogin(){

        assertUtil.assertEditable(ConfigReader.getUsername());
        assertUtil.assertEditable(ConfigReader.getPassword());
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickLogin();
        assertUtil.assertPageTitleContains(page,"Internet");
    }

}
