package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    Page page;
    ConfigReader configReader;

    public PlaywrightFactory(){
        configReader = new ConfigReader();
    }

    public Page initBrowser(){
        String browserType = ConfigReader.getProperty("browser");

        playwright = Playwright.create();

        switch(browserType.toLowerCase()){

            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(true));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(true));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
        page = browser.newPage();
//        int width=1710; //Macbook only
//        int height = 993; //Macbook only
//        page.setViewportSize(width,height);
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
