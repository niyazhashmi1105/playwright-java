package configurator;
import org.aeonbits.owner.Config;

@Config.Sources("file:${user.dir}/src/main/resources/playwright-config.properties")
public interface IPlaywrightAppConfig extends Config {

    @Key("browser")
    String browser();

    @Key("base.url")
    String baseUrl();

    @Key("headless")
    boolean headless();

    @Key("slow.motion")
    int slowMotion();


}