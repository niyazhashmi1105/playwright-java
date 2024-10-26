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

    /**
     * Initializes the testing environment before each test method.
     *
     * This method is annotated with @BeforeMethod, which indicates that it will be executed
     * before each test method in the class. It performs the following tasks:
     * <ul>
     *     <li>Creates an instance of the {@link PlaywrightFactory} to initialize the Playwright browser.</li>
     *     <li>Initializes the browser and creates a new page using {@link PlaywrightFactory#initBrowser()}.</li>
     *     <li>Instantiates the {@link TestUtil} class with the initialized page for utility functions.</li>
     *     <li>Sets the current page in the {@link ReportListener} for reporting purposes.</li>
     *     <li>Initializes instances of {@link LoginPage} and {@link HomePage} to facilitate navigation and interactions during the tests.</li>
     *     <li>Creates an instance of {@link AssertUtil} for assertion methods to validate test outcomes.</li>
     *     <li>Logs the step to Allure reporting for visibility in the generated reports.</li>
     * </ul>
     *
     * This method ensures that the necessary components are set up and ready for each test,
     * providing a clean state and preventing test interference.
     *
     * @throws Exception If there is an error during the browser initialization or page creation.
     */
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

    /**
     * Cleans up the testing environment after each test method.
     *
     * This method is annotated with @AfterMethod, indicating that it will be executed
     * after each test method in the class. It performs the following tasks:
     * <ul>
     *     <li>Logs a step in Allure reporting to indicate that the browser is being closed.</li>
     *     <li>Calls the {@link PlaywrightFactory#tearDown()} method to close the browser instance and release resources.</li>
     * </ul>
     *
     * This method ensures that all browser instances are properly closed after each test
     * execution, helping to prevent resource leaks and ensuring that tests do not
     * interfere with one another.
     *
     * @throws Exception If there is an error during the teardown process, such as failure to close the browser.
     */
    @AfterMethod
    public void tearDown(){
        Allure.step("Closing the browser");
        playwrightFactory.tearDown();
    }
}
