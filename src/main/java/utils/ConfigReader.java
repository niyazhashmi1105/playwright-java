package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties playwrightConfig;
    private static Properties locatorConfig;

    public ConfigReader() {
        playwrightConfig = new Properties();
        locatorConfig = new Properties();
        loadPlaywrightConfig();
        loadLocatorConfig();
    }
    private void loadPlaywrightConfig(){

        try(FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//playwright-config.properties")){
            playwrightConfig = new Properties();
            playwrightConfig.load(fis);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load playwright config properties file "+ e.getMessage());
        }
    }

    private void loadLocatorConfig(){

        try(FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//locators.properties")){
            locatorConfig = new Properties();
            locatorConfig.load(fis);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load locator config properties file "+ e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return playwrightConfig.getProperty(key);
    }

    public static String getLocator(String key) {
        return locatorConfig.getProperty(key);
    }


}
