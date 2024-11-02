package utils;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.ScreenshotType;
import io.qameta.allure.Allure;

import java.util.Base64;

/**
 * The {@code TestUtil} class provides utility methods for interacting
 * with web page elements using Playwright's {@link Page} class.
 * It includes methods for handling alerts, clicking elements,
 * typing text, waiting for elements, and capturing screenshots.
 */
public class ElementActionUtils {

    private final Page page;

    /**
     * Constructs a {@code TestUtil} instance with the specified {@code Page}.
     *
     * @param page the {@link Page} object used for interacting with web elements.
     */
    public ElementActionUtils(Page page) {
        this.page = page;
    }


    /**
     * Handles any alerts present on the page by accepting them.
     * This method invokes the accept action on the dialog.
     */
    public void handleAlert() {
        page.onDialog(Dialog::accept);
        Allure.step("Handled alert by accepting");
    }

    /**
     * Clicks on an element specified by the locator key.
     *
     * @param locatorKey the key to retrieve the locator from the configuration.
     */
    public void click(String locatorKey){
        page.click(locatorKey);
        Allure.step("Clicked on element: " + locatorKey);
        ExtentTestManager.getTest().log(Status.INFO,"Clicked on element: " + locatorKey);
    }

    /**
     * Types the specified value into an input field identified by the locator key.
     *
     * @param locatorKey the key to retrieve the locator from the configuration.
     * @param value      the value to be typed into the input field.
     */
    public void type(String locatorKey, String value){
        page.fill(locatorKey,value);
        Allure.step("Entered value '" + value + "' into field: " + locatorKey);
        if(locatorKey.contains("password")){
            try {
                String encryptedPassword = EncryptionUtil.encrypt(value);
                Allure.step("Entered value into field: " + locatorKey);
                ExtentTestManager.getTest().log(Status.INFO, "Entered value into field: " + locatorKey);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            ExtentTestManager.getTest().log(Status.INFO, "Entered value '" + value + "' into field: " + locatorKey);
        }
    }

    /**
     * Waits for an element specified by the locator key to become visible.
     *
     * @param locatorKey the key to retrieve the locator from the configuration.
     */
    public void waitForElementVisible(String locatorKey) {
        //String selector = ConfigReader.getLocator(locatorKey);
        page.waitForSelector(locatorKey);
        Allure.step("Waited for element to be visible: " + locatorKey);
        ExtentTestManager.getTest().log(Status.INFO,"Waited for element to be visible: " + locatorKey);
    }

    /**
     * Captures a screenshot of the current page and returns it as a Base64-encoded string.
     *
     * @return a Base64-encoded string representation of the screenshot in PNG format.
     */
    public String captureScreenshotAsBase64() {
        byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setType(ScreenshotType.PNG));
        return Base64.getEncoder().encodeToString(screenshotBytes);
    }

}
