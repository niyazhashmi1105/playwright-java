package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import configurator.ConfigReader;

import java.util.Map;
import java.util.function.Supplier;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;

    public PlaywrightFactory(){
        new ConfigReader();
    }

    /**
     * Initializes the browser based on the specified configuration properties and returns a {"@link Page"} object.
     * This method reads the browser configuration from a properties file using {"@link ConfigReader"}.
     * It supports launching the following browsers:
     * <ul>
     *     <li>Chrome</li>
     *     <li>Firefox</li>
     *     <li>Webkit (Safari)</li>
     *     <li>Microsoft Edge</li>
     * </ul>
     *
     * Based on the provided configuration, it launches the browser in either headless or non-headless mode
     * and applies an optional slow-motion delay. It then navigates to the base URL and returns the corresponding {"@link Page"} object.
     *
     * @return A {"@link Page"} object that can be used to interact with the web page.
     *
     * "@throws IllegalArgumentException" if the provided browser type is not supported.
     */

    public Page initBrowser() {

        String browserType = ConfigReader.getBrowser();
        boolean isHeadless = ConfigReader.isHeadless();
        int slowMo = ConfigReader.getSlowMotion();

        playwright = Playwright.create();

        Map<String, Supplier<Browser>> browserMap = Map.of(
                "chrome",() -> playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless).setSlowMo(slowMo)),
                "firefox",() -> playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless).setSlowMo(slowMo)),
                "webkit",() -> playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless).setSlowMo(slowMo)),
                "edge",() -> playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(isHeadless).setSlowMo(slowMo))
        );
        browser = browserMap.entrySet().stream()
                .filter(entry -> entry.getKey().equals(browserType.toLowerCase().trim()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Unsupported browser: " + browserType))
                .get();

        Page page = browser.newPage();
        page.navigate(ConfigReader.getURL());
        return page;
    }

    /**
     * Closes the browser and Playwright instance to clean up resources after the tests are executed.
     * This method ensures that both the {"@link Browser"} and {"@link Playwright"} instances are properly closed if they are initialized.
     * It performs the following steps:
     * <ul>
     *     <li>If the {"@link Browser"} instance is not null, it closes the browser.</li>
     *     <li>If the "{"@link Playwright"} instance is not null, it closes the Playwright context.</li>
     * </ul>
     *
     * This method should be called after tests are executed to free up system resources and avoid memory leaks.
     */
    
    public void tearDown(){

        if (browser != null) {
            browser.close();
        }
        if(playwright != null){
            playwright.close();
        }
    }
}
