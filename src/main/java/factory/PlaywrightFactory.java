package factory;

import com.microsoft.playwright.*;
import configurator.ConfigReader;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private final String browserStackUsername = "mdniyazhashmi_h1Ux6p";
    private final String browserStackAccessKey = "iTE5EC6uHJzsupUwszRC";

    public PlaywrightFactory() {
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
     * <p>
     * Based on the provided configuration, it launches the browser in either headless or non-headless mode
     * and applies an optional slow-motion delay. It then navigates to the base URL and returns the corresponding {"@link Page"} object.
     *
     * @return A {"@link Page"} object that can be used to interact with the web page.
     * <p>
     * "@throws IllegalArgumentException" if the provided browser type is not supported.
     */

    public Page initBrowser() {

        String environment = ConfigReader.getEnvironment();
        System.out.println("Environment: " + environment);
        String baseUrl = ConfigReader.getURL();
        System.out.println("BaseUrl: " + baseUrl);
        String browserType = ConfigReader.getBrowser();
        System.out.println("Browser Name: " + browserType);
        boolean isHeadless = ConfigReader.isHeadless();
        int slowMo = ConfigReader.getSlowMotion();

        playwright = Playwright.create();

        Map<String, Supplier<Browser>> browserMap = Map.of(
                "chrome", () -> playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless).setSlowMo(slowMo)),
                "firefox", () -> playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless).setSlowMo(slowMo)),
                "webkit", () -> playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless).setSlowMo(slowMo)),
                "edge", () -> playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(isHeadless).setSlowMo(slowMo)),
                "browserstack",this::connectToBrowserStack
        );
        browser = browserMap.entrySet().stream()
                .filter(entry -> entry.getKey().equals(browserType.toLowerCase().trim()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported browser: " + browserType))
                .get();
        context = browser.newContext();
        page = context.newPage();
        page.navigate(baseUrl);
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
     * <p>
     * This method should be called after tests are executed to free up system resources and avoid memory leaks.
     */

    public void tearDown() {

        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    private Browser connectToBrowserStack() {
        try {
            // BrowserStack capabilities
            Map<String, String> capabilities = new HashMap<>();
            capabilities.put("browser", "chrome");
            capabilities.put("browser_version", ConfigReader.getBrowserVersion());
            capabilities.put("os", ConfigReader.getOS());
            capabilities.put("os_version", ConfigReader.getOSVersion());
            capabilities.put("name", ConfigReader.getBSProjectName());
            capabilities.put("build", ConfigReader.getBSBuildName());

            // Construct the BrowserStack connection URL
            String browserstackUrl = String.format(
            "wss://%s:%s@cdp.browserstack.com/playwright?caps=%s",
                   ConfigReader.getBrowserStackUserName(),
                    ConfigReader.getBrowserStackAccessKey(),
                    URLEncoder.encode(new JSONObject(capabilities).toString(), StandardCharsets.UTF_8));

            // Connect to BrowserStack using the URL
            return playwright.chromium().connect(browserstackUrl);
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to BrowserStack", e);
        }
    }

}
