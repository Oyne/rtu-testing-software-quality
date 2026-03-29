package orangeHRM.driver;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:driver.properties"})
public interface DriverConfig extends Config {
    @Key("baseUrl") String baseUrl();
    @Key("browser") String browser();
    @Key("headless") boolean headless();
    @Key("maximize") boolean maximize();
    @Key("remoteOrigins") String remoteOrigins();
}