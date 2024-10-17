package utils;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.testng.Assert;

public class TestUtil {

    private Page page;

    public TestUtil(Page page) {
        this.page = page;
    }

    // Common method to assert title
    public void assertContainsPageTitle(String expectedTitle) {
        Assert.assertTrue(page.title().contains(expectedTitle));
        Allure.step("Contains page title: " + expectedTitle);
    }

    // Common method to assert title
    public void assertPageTitle(String expectedTitle) {
        String actualTitle = page.title();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match");
        Allure.step("Verified page title: " + expectedTitle);
    }

    // Verify that an element contains specific text
    public void assertElementText(String selector, String expectedText) {
        String actualText = page.textContent(selector);
        Assert.assertEquals(actualText, expectedText, "Element text does not match");
        Allure.step("Verified element text for selector: " + selector + ", Expected: " + expectedText);
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
    }

    // Method to type text into a field
    public void type(String locatorKey, String value){
        String selector = ConfigReader.getLocator(locatorKey);
        page.fill(selector,value);
        Allure.step("Entered value '" + value + "' into field: " + locatorKey);
    }

    // Method to wait for an element to be visible
    public void waitForElementVisible(String locatorKey) {
        String selector = ConfigReader.getLocator(locatorKey);
        page.waitForSelector(selector);
        Allure.step("Waited for element to be visible: " + locatorKey);
    }

    // Method to check an element to be visible
    public boolean isElementVisible(String locatorKey) {
        String selector = ConfigReader.getLocator(locatorKey);
        boolean isVisible = page.isVisible(selector);
        Allure.step("Is element to be visible: " + locatorKey);
        return isVisible;
    }

    public String getPageURL(){
        return page.url();
    }

}
