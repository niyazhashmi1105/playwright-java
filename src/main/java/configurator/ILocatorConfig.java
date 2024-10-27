package configurator;
import org.aeonbits.owner.Config;


@Config.Sources("file:${user.dir}/src/main/resources/locators.properties")
public interface ILocatorConfig extends Config {

    @Key("login.username")
    String username();

    @Key("login.password")
    String password();

    @Key("login.btnLogin")
    String loginBtn();

    @Key("home.loggedInText")
    String homePageText();

    @Key("home.btnLogout")
    String homePageLogoutBtn();

    @Key("home.pageTitle")
    String homePageTitle();
}