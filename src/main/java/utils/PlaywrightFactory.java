package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    Page page;

    public PlaywrightFactory(){
        new ConfigReader();
    }

    public Page initBrowser(){

        String browserType = ConfigReader.getProperty("browser");
        playwright = Playwright.create();

            switch (browserType.toLowerCase()) {

                case "chrome":
                    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(ConfigReader.getProperty("headless"))).setSlowMo(Double.parseDouble(ConfigReader.getProperty("slow.motion"))));
                    break;
                case "firefox":
                    browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(ConfigReader.getProperty("headless"))).setSlowMo(Double.parseDouble(ConfigReader.getProperty("slow.motion"))));
                    break;
                case "webkit":
                    browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(ConfigReader.getProperty("headless"))).setSlowMo(Double.parseDouble(ConfigReader.getProperty("slow.motion"))));
                    break;
                case "msedge":
                    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(Boolean.parseBoolean(ConfigReader.getProperty("headless"))).setSlowMo(Double.parseDouble(ConfigReader.getProperty("slow.motion"))));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserType);
            }

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
