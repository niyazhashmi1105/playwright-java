package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.TestUtil;

//@Listeners(ReportListener.class)
public class LoginPageTest extends BaseTest {

    @Test
    public void verifyPageTitleOnSuccessfulLogin(){
        TestUtil testUtil = new TestUtil(page);
        LoginPage loginPage = new LoginPage(testUtil);
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickLogin();
        testUtil.assertPageTitle("The Internet");
    }

    @Test
    public void verifyInvalidPageTitleOnSuccessfulLogin(){
        TestUtil testUtil = new TestUtil(page);
        LoginPage loginPage = new LoginPage(testUtil);
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickLogin();
        //testUtil.assertContainsPageTitle("internet");
    }

}
