package base;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PlaywrightFactory;
import listeners.AllureListener;

public class BaseTest {

    protected PlaywrightFactory playwrightFactory;
    protected Page page;

    @BeforeMethod
    public void setUp(){
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.initBrowser();
        Allure.step("Browser launched and page initialized");
        AllureListener.setPage(page);
    }

    @AfterMethod
    public void tearDown(){
        Allure.step("Closing the browser");
        playwrightFactory.tearDown();
    }


}
