package base;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import listeners.ReportListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PlaywrightFactory;
import utils.TestUtil;

public class BaseTest {

    protected PlaywrightFactory playwrightFactory;
    protected Page page;
    public TestUtil testUtil;

    @BeforeMethod
    public void setUp(){
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.initBrowser();
        testUtil = new TestUtil(page);
        ReportListener.setPage(page);
        Allure.step("Browser launched and page initialized");
    }

    @AfterMethod
    public void tearDown(){
        Allure.step("Closing the browser");
        playwrightFactory.tearDown();
    }


}
