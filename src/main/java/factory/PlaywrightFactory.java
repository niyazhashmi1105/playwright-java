package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import utils.ConfigReader;

import java.util.Map;
import java.util.function.Supplier;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    public PlaywrightFactory(){
        new ConfigReader();
    }

    public Page initBrowser() {
        String browserType = ConfigReader.getProperty("browser").toLowerCase();
        boolean isHeadless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));
        double slowMo = Double.parseDouble(ConfigReader.getProperty("slow.motion"));

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

        page = browser.newPage();
        page.navigate(ConfigReader.getProperty("base.url"));
        return page;
    }

    public void tearDown(){

        if (browser != null) {
            browser.close();
        }
        if(playwright != null){
            playwright.close();
        }
    }


}
