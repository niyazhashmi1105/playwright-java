package configurator;

import org.aeonbits.owner.ConfigFactory;


/**
 * The {@code ConfigReader} class is responsible for loading and providing
 * configuration properties for the Playwright framework and locators.
 * It reads property files from the specified resources directory and
 * allows access to these properties throughout the application.
 * The class ensures that properties are loaded during initialization,
 * and it provides static methods to retrieve specific properties or locators
 * by their keys.
 */

public class ConfigReader {

    private static final IPlaywrightAppConfig playwrightAppConfig = ConfigFactory.create(IPlaywrightAppConfig.class);
    private static final ILocatorConfig locatorsConfig = ConfigFactory.create(ILocatorConfig.class);

    /**
     * Constructs a new {@code ConfigReader} instance.
     * This constructor initializes the properties for Playwright
     * and locators by calling the respective loading methods.
     * If any of the property files fail to load, an
     * {@code ExceptionInInitializerError} is thrown.
     */
    public ConfigReader() {}

    public static String getEnvironment() {
        return playwrightAppConfig.getEnv();
    }

    public static String getBrowser() {
        return playwrightAppConfig.browser();
    }

    public static String getURL() {
        return playwrightAppConfig.baseUrl();
    }

    public static boolean isHeadless() {
        return playwrightAppConfig.headless();
    }

    public static int getSlowMotion() {
        return playwrightAppConfig.slowMotion();
    }

    public static String getUsername() {
        return locatorsConfig.username();
    }

    public static String getPassword() {
        return locatorsConfig.password();
    }

    public static String getLoginBtn() {
        return locatorsConfig.loginBtn();
    }

    public static String getHomePageText() {
        return locatorsConfig.homePageText();
    }

    public static String getLogoutBtn() {
        return locatorsConfig.homePageLogoutBtn();
    }

    public static String getHomePageTitle() {
        return locatorsConfig.homePageTitle();
    }
    public static String getSearchText() {
        return locatorsConfig.googleTextArea();
    }

    public static String getBrowserStackUserName(){
        return playwrightAppConfig.browserStackUsername();
    }

    public static String getBrowserStackAccessKey(){
        return playwrightAppConfig.browserStackAccessKey();
    }

    public static String getBrowserVersion(){
        return playwrightAppConfig.browserVersion();
    }

    public static String getOS(){
        return playwrightAppConfig.os();
    }

    public static String getOSVersion(){
        return playwrightAppConfig.osVersion();
    }

    public static String getBSProjectName(){
        return playwrightAppConfig.projectName();
    }

    public static String getBSBuildName(){
        return playwrightAppConfig.buildName();
    }

    public static String getDeviceName(){
        return playwrightAppConfig.device();
    }

}
