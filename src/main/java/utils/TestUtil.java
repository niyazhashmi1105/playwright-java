package utils;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.ScreenshotType;
import io.qameta.allure.Allure;

import java.util.Base64;

public class TestUtil {

    private final Page page;

    public TestUtil(Page page) {
        this.page = page;
    }


    // Handle alerts (if needed)
    public void handleAlert() {
        page.onDialog(Dialog::accept);
        Allure.step("Handled alert by accepting");
    }

    // Method to click an element
    public void click(String locatorKey){
        String  selector = ConfigReader.getLocator(locatorKey);
        page.click(selector);
        Allure.step("Clicked on element: " + locatorKey);
        ExtentTestManager.getTest().log(Status.INFO,"Clicked on element: " + locatorKey);
    }

    // Method to type text into a field
    public void type(String locatorKey, String value){
        String selector = ConfigReader.getLocator(locatorKey);
        page.fill(selector,value);
        Allure.step("Entered value '" + value + "' into field: " + locatorKey);
        ExtentTestManager.getTest().log(Status.INFO,"Entered value '" + value + "' into field: " + locatorKey);
    }

    // Method to wait for an element to be visible
    public void waitForElementVisible(String locatorKey) {
        //String selector = ConfigReader.getLocator(locatorKey);
        page.waitForSelector(locatorKey);
        Allure.step("Waited for element to be visible: " + locatorKey);
        ExtentTestManager.getTest().log(Status.INFO,"Waited for element to be visible: " + locatorKey);
    }

    public String captureScreenshotAsBase64() {
        byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setType(ScreenshotType.PNG));
        return Base64.getEncoder().encodeToString(screenshotBytes);
    }

}
