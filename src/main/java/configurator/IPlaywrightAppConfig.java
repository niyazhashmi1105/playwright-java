package configurator;
import org.aeonbits.owner.Config;

@Config.Sources("file:${user.dir}/src/main/resources/env/${env}/config.properties")
public interface IPlaywrightAppConfig extends Config {

    @Key("environment")
    @DefaultValue("qa")
    String getEnv();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("base.url")
    @DefaultValue("https://the-internet.herokuapp.com/login")
    String baseUrl();

    @Key("headless")
    @DefaultValue("true")
    boolean headless();

    @Key("slow.motion")
    @DefaultValue("150")
    int slowMotion();


}