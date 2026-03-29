package orangeHRM.driver;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final DriverConfig cfg = ConfigFactory.create(DriverConfig.class);

    public static synchronized WebDriver getDriver() {
        if (driver.get() == null) {
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--remote-allow-origins=" + cfg.remoteOrigins());
            if (cfg.maximize()) options.addArguments("--start-maximized");
            if (cfg.headless()) options.addArguments("--headless=new");

           /* System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "/" + cfg.driverPath());*/

            driver.set(new ChromeDriver(options));
            driver.get().get(cfg.baseUrl());
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
