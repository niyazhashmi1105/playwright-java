package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


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

    private static Properties playwrightConfig;
    private static Properties locatorConfig;

    /**
     * Constructs a new {@code ConfigReader} instance.
     * This constructor initializes the properties for Playwright
     * and locators by calling the respective loading methods.
     * If any of the property files fail to load, an
     * {@code ExceptionInInitializerError} is thrown.
     */
    public ConfigReader() {
        playwrightConfig = new Properties();
        locatorConfig = new Properties();
        loadPlaywrightConfig();
        loadLocatorConfig();
    }

    /**
     * Loads the Playwright configuration properties from the
     * {@code playwright-config.properties} file.
     * This method reads the properties file located in the
     * {@code src/main/resources} directory and populates the
     * {@code playwrightConfig} property. If the file cannot be
     * found or read, an {@code ExceptionInInitializerError}
     * is thrown with an appropriate message.
     *
     * @throws ExceptionInInitializerError if the Playwright config
     *         properties file cannot be loaded.
     */
    private void loadPlaywrightConfig(){

        try(FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//playwright-config.properties")){
            playwrightConfig = new Properties();
            playwrightConfig.load(fis);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load playwright config properties file "+ e.getMessage());
        }
    }

    /**
     * Loads the locator configuration properties from the
     * {@code locators.properties} file.
     * This method reads the properties file located in the
     * {@code src/main/resources} directory and populates the
     * {@code locatorConfig} property. If the file cannot be
     * found or read, an {@code ExceptionInInitializerError}
     * is thrown with an appropriate message.
     *
     * @throws ExceptionInInitializerError if the locator config
     *         properties file cannot be loaded.
     */
    private void loadLocatorConfig(){

        try(FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//locators.properties")){
            locatorConfig = new Properties();
            locatorConfig.load(fis);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load locator config properties file "+ e.getMessage());
        }
    }

    /**
     * Retrieves a property value from the Playwright configuration.
     *
     * @param key the key of the property to be retrieved.
     * @return the value associated with the specified key, or {@code null}
     *         if the key does not exist in the properties.
     */
    public static String getProperty(String key) {
        return playwrightConfig.getProperty(key);
    }

    /**
     * Retrieves a locator value from the locator configuration.
     *
     * @param key the key of the locator to be retrieved.
     * @return the value associated with the specified key, or {@code null}
     *         if the key does not exist in the properties.
     */
    public static String getLocator(String key) {
        return locatorConfig.getProperty(key);
    }


}
