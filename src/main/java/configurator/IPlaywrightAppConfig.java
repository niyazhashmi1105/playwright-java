package configurator;
import org.aeonbits.owner.Config;

@Config.Sources("file:${user.dir}/src/main/resources/env/${env}/${env}-config.properties")
public interface IPlaywrightAppConfig extends Config {

    @Key("env")
    String getEnv();

    @Key("browser")
    String browser();

    @Key("base.url")
    String baseUrl();

    @Key("headless")
    @DefaultValue("true")
    boolean headless();

    @Key("slow.motion")
    @DefaultValue("150")
    int slowMotion();

    @Key("browserstack.username")
    String browserStackUsername();

    @Key("browserstack.accessKey")
    String browserStackAccessKey();

    @Key("browserstack.browserVersion")
    String browserVersion();

    @Key("browserstack.os")
    String os();

    @Key("browserstack.osVersion")
    String osVersion();

    @Key("browserstack.projectName")
    @DefaultValue("Playwright-Java")
    String projectName();

    @Key("browserstack.buildName")
    @DefaultValue("Playwright-Build")
    String buildName();

    @Key("browserstack.device")
    String device();

}