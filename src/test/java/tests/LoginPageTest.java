package tests;

import base.BaseTest;
import listeners.AllureListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.TestUtil;

@Listeners(AllureListener.class)
public class LoginPageTest extends BaseTest {

    @Test
    public void validatePageTitleOnSuccessfulLogin(){
        TestUtil testUtil = new TestUtil(page);
        LoginPage loginPage = new LoginPage(testUtil);
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickLogin();
        testUtil.assertPageTitle("The Internet");
    }

    @Test
    public void validateInvalidPageTitleOnSuccessfulLogin(){
        TestUtil testUtil = new TestUtil(page);
        LoginPage loginPage = new LoginPage(testUtil);
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickLogin();
        testUtil.assertContainsPageTitle("internet");
    }

}
