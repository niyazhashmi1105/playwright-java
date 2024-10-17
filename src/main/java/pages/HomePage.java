package pages;

import utils.TestUtil;

public class HomePage {

    private TestUtil testUtil;

    public HomePage(TestUtil testUtil){
        this.testUtil = testUtil;

    }

    public boolean isElementVisible(){
        return testUtil.isElementVisible("home.btnlogout");
    }

    public void clickLogoutButton(){
        testUtil.click("home.btnlogout");
    }
}
