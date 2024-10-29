package configurator;
import org.aeonbits.owner.Config;

@Config.Sources("file:${user.dir}/src/main/resources/env/${env}/config.properties")
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
}