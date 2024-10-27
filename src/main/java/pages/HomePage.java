package pages;

import configurator.ConfigReader;
import utils.TestUtil;

public class HomePage {

    private final TestUtil testUtil;

    public HomePage(TestUtil testUtil){
        this.testUtil = testUtil;

    }

    /**
     * Clicks the logout button on the home page.
     * This method utilizes the {@link TestUtil} class to perform a click action
     * on the logout button identified by the locator key "home.btnLogout".
     * This action effectively logs the user out of the application,
     * returning them to the login screen or the initial landing page.
     *
     * <p>
     * It is important to ensure that the user is logged in before calling
     * this method, as clicking the logout button without an active session
     * may lead to unexpected behavior or errors.
     * </p>
     */
    public void clickLogoutButton(){
        testUtil.click(ConfigReader.getLogoutBtn());
    }
}
