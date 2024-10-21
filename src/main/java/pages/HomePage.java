package pages;

import utils.TestUtil;

public class HomePage {

    private final TestUtil testUtil;

    public HomePage(TestUtil testUtil){
        this.testUtil = testUtil;

    }

    public void clickLogoutButton(){
        testUtil.click("home.btnLogout");
    }
}
