package orangeHRM.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final DriverConfig cfg = ConfigFactory.create(DriverConfig.class);

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            if (!cfg.remoteOrigins().isEmpty()) {
                options.addArguments("--remote-allow-origins=" + cfg.remoteOrigins());
            }

            if (cfg.maximize()) {
                options.addArguments("--start-maximized");
            }

            if (cfg.headless()) {
                options.addArguments("--headless=new");
            }

            WebDriver webDriver = new ChromeDriver(options);

            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            driver.set(webDriver);

            driver.get().get(cfg.baseUrl());
        }
        return driver.get();
    }

    public static void quitDriver() {
        WebDriver webDriver = driver.get();

        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}
