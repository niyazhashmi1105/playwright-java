package utils;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AssertUtil {

    private final Page page;

    public AssertUtil(Page page){
        this.page = page;
    }

    /**
     * Assert that the element is visible on the page.
     * @param locatorKey the Playwright locator of the element passed from properties file.
     */
    public void assertVisible(String locatorKey) {
        assertThat(page.locator(locatorKey)).isVisible();
    }

    /**
     * Assert that the element is hidden or not visible on the page.
     * @param locatorKey the Playwright locator of the element passed from properties file.
     */
    public void assertHidden(String locatorKey) {
        assertThat(page.locator(locatorKey)).isHidden();
    }

    /**
     * Assert that the element contains the expected text.
     * @param locatorKey the Playwright locator of the element.
     * @param expectedText the expected text.
     */
    public void assertTextContains(String locatorKey, String expectedText) {
        assertThat(page.locator(locatorKey)).containsText(expectedText);
    }


    /**
     * Assert that the element contains the expected text.
     * @param page the Playwright title of the page.
     * @param expectedText the expected text.
     */
    public void assertPageTitleContains(Page page, String expectedText) {
        String actualTitle = page.title();
        assertTrue(actualTitle.contains(expectedText),"There is mismatch in the page title");
    }

    /**
     * Assert that the element has the exact expected text.
     * @param locatorKey the Playwright locator of the element.
     * @param expectedText the exact expected text.
     */
    public void assertTextEquals(String locatorKey, String expectedText) {

        String actualText;
        System.out.println(page.locator(locatorKey).textContent().trim());
        if (page.locator(locatorKey).textContent().contains(" ")){
            actualText = page.locator(locatorKey).textContent().trim();
            assertThat(page.locator(actualText)).containsText(expectedText);
        }
        else{
            assertThat(page.locator(locatorKey)).hasText(expectedText);
        }
    }

    /**
     * Assert that the element has a specific attribute with the expected value.
     * @param locatorKey the Playwright locator of the element.
     * @param attribute the attribute to check.
     * @param expectedValue the expected value of the attribute.
     */
    public void assertAttribute(String locatorKey, String attribute, String expectedValue) {
        assertThat(page.locator(locatorKey)).hasAttribute(attribute, expectedValue);
    }

    /**
     * Assert that the page URL contains the expected substring.
     * @param page the Playwright page instance.
     * @param expectedUrlPart the substring expected in the URL.
     */
    public void assertUrlContains(Page page, String expectedUrlPart) {
        String currentUrl = page.url();
        assertTrue(currentUrl.contains(expectedUrlPart),
                "Expected URL to contain: " + expectedUrlPart + " but was: " + currentUrl);
    }

    /**
     * Assert that the page URL matches the expected URL.
     * @param page the Playwright page instance.
     * @param expectedUrl the expected exact URL.
     */
    public static void assertUrlEquals(Page page, String expectedUrl) {
        assertThat(page).hasURL(expectedUrl);
    }

    /**
     * Assert that the element is enabled (not disabled).
     * @param locatorKey the Playwright locator of the element.
     */
    public void assertEnabled(String locatorKey) {
        assertThat(page.locator(locatorKey)).isEnabled();
    }

    /**
     * Assert that the element is disabled.
     * @param locatorKey the Playwright locator of the element.
     */
    public void assertDisabled(String locatorKey) {
        assertThat(page.locator(locatorKey)).isDisabled();
    }

    /**
     * Assert that the element is editable (not readonly).
     * @param locatorKey the Playwright locator of the element.
     */
    public void assertEditable(String locatorKey) {
        assertThat(page.locator(locatorKey)).isEditable();
    }

    /**
     * Assert that the element is not editable (readonly).
     * @param locatorKey the Playwright locator of the element.
     */
    public void assertNotEditable(String locatorKey) {
        assertThat(page.locator(locatorKey)).not().isEditable();
    }

    /**
     * Assert that the element is empty or has no inner content.
     * @param locatorKey the Playwright locator of the element.
     */
    public void assertEmpty(String locatorKey) {
        assertThat(page.locator(locatorKey)).isEmpty();
    }

    /**
     * Assert that the element has the expected CSS value.
     * @param locatorKey the Playwright locator of the element.
     * @param cssProperty the CSS property to check.
     * @param expectedValue the expected value of the CSS property.
     */
    public void assertCssValue(String locatorKey, String cssProperty, String expectedValue) {
        assertThat(page.locator(locatorKey)).hasCSS(cssProperty, expectedValue);
    }

    /**
     * Assert that the checkbox or radio button is checked.
     * @param locatorKey the Playwright locator of the element.
     */
    public void assertChecked(String locatorKey) {
        assertThat(page.locator(locatorKey)).isChecked();
    }

    /**
     * Assert that the checkbox or radio button is not checked.
     * @param locatorKey the Playwright locator of the element.
     */
    public void assertUnchecked(String locatorKey) {
        boolean isChecked = page.locator(locatorKey).isChecked();
        assertFalse(isChecked, "Expected element to be unchecked, but it was checked.");
    }

    /**
     * Assert that the element has the expected number of elements in the list.
     * @param locatorKey the Playwright locator of the elements.
     * @param expectedCount the expected number of elements.
     */
    public void assertElementCount(String locatorKey, int expectedCount) {
        assertThat(page.locator(locatorKey)).hasCount(expectedCount);
    }

    /**
     * Assert that the element is focused.
     * @param locatorKey the Playwright locator of the element.
     */
    public void assertFocused(String locatorKey) {
        assertThat(page.locator(locatorKey)).isFocused();
    }

    /**
     * Assert that the element is not focused.
     * @param locatorKey the Playwright locator of the element.
     */
    public void assertNotFocused(String locatorKey) {
        assertThat(page.locator(locatorKey)).not().isFocused();
    }
}
