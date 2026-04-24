package orangeHRM.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class DriverManager {

    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final DriverConfig cfg = ConfigFactory.create(DriverConfig.class);

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            log.info("Initializing new Chrome Driver session");

            try {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();

                if (!cfg.remoteOrigins().isEmpty()) {
                    log.debug("Setting remote-allow-origins: {}", cfg.remoteOrigins());
                    options.addArguments("--remote-allow-origins=" + cfg.remoteOrigins());
                }

                if (cfg.maximize()) {
                    log.debug("Setting chrome argument: --start-maximized");
                    options.addArguments("--start-maximized");
                }

                if (cfg.headless()) {
                    log.info("Browser is starting in HEADLESS mode.");
                    options.addArguments("--headless=new");
                }

                ChromeDriver webDriver = new ChromeDriver(options);
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                driver.set(webDriver);

                log.info("Chrome Driver initialized successfully. Session ID: {}",
                        webDriver.getSessionId());

                log.info("Navigating to Base URL: {}", cfg.baseUrl());
                driver.get().get(cfg.baseUrl());

            } catch (Exception e) {
                log.error("Failed to initialize WebDriver: {}", e.getMessage());
                throw e;
            }
        }
        return driver.get();
    }

    public static void quitDriver() {
        WebDriver webDriver = driver.get();

        if (webDriver != null) {
            log.info("Quitting driver and closing browser session.");
            webDriver.quit();
            driver.remove();
            log.debug("Driver removed from ThreadLocal.");
        } else {
            log.warn("Attempted to quit driver, but no driver session was found for the current thread.");
        }
    }
}