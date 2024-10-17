package pages;

import utils.TestUtil;

public class HomePage {

    private final TestUtil testUtil;

    public HomePage(TestUtil testUtil){
        this.testUtil = testUtil;

    }

    public boolean isElementVisible(){
        return testUtil.isElementVisible("home.btnlogout");
    }

    public void clickLogoutButton(){
        testUtil.waitForElementVisible("home.btnlogout");
        testUtil.click("home.btnlogout");
    }
}
