package pages;

import utils.TestUtil;

public class LoginPage {

    private TestUtil testUtil;

    public LoginPage(TestUtil testUtil){
        this.testUtil = testUtil;

    }

    public void enterCredentials(String username, String password){
       testUtil.type("login.username",username);
       testUtil.type("login.password",password);
    }

    public void clickLogin(){
        testUtil.click("login.btnlogin");
    }
}
