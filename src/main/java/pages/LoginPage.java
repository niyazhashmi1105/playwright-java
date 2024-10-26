package pages;

import utils.TestUtil;

public class LoginPage {

    private final TestUtil testUtil;

    public LoginPage(TestUtil testUtil){
        this.testUtil = testUtil;
    }

    /**
     * Enters the provided username and password into the login form.
     * This method utilizes the {@link TestUtil} class to fill in the
     * username and password fields on the login page. The input fields
     * are identified by the locator keys "login.username" and "login.password",
     * respectively. After calling this method, the login credentials will be
     * populated, allowing the user to submit the login form.
     *
     * @param username the username to be entered into the username field.
     *                 This should be a valid username that exists in the
     *                 application's user database.
     * @param password the password associated with the provided username.
     *                 This should be the correct password for the username to
     *                 successfully log in.
     *
     * @throws "ElementNotInteractableException" if the username or password
     *         fields are present but cannot be interacted with. This could happen
     *         if the elements are disabled or obscured.
     */
    public void enterCredentials(String username, String password){
       testUtil.type("login.username",username);
       testUtil.type("login.password",password);
    }

    public void clickLogin(){
        testUtil.click("login.btnLogin");
    }
}
