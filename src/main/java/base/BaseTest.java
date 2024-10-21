package base;

import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;
import io.qameta.allure.Allure;
import listeners.ReportListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;
import utils.AssertUtil;
import utils.TestUtil;

public class BaseTest {

    protected PlaywrightFactory playwrightFactory;
    protected Page page;
    public TestUtil testUtil;
    public LoginPage loginPage;
    public AssertUtil assertUtil;
    public HomePage homePage;

    @BeforeMethod
    public void setUp(){
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.initBrowser();
        testUtil = new TestUtil(page);
        ReportListener.setPage(page);
        loginPage = new LoginPage(testUtil);
        homePage = new HomePage(testUtil);
        assertUtil = new AssertUtil(page);
        Allure.step("Browser launched and page initialized");
    }

    @AfterMethod
    public void tearDown(){
        Allure.step("Closing the browser");
        playwrightFactory.tearDown();
    }


}
