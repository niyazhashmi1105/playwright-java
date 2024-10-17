package base;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import listeners.AllureListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PlaywrightFactory;

public class BaseTest {

    protected PlaywrightFactory playwrightFactory;
    protected Page page;

    @BeforeMethod
    public void setUp(){
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.initBrowser();
        AllureListener.setPage(page);
        Allure.step("Browser launched and page initialized");

    }

    @AfterMethod
    public void tearDown(){
        Allure.step("Closing the browser");
        playwrightFactory.tearDown();
    }


}
