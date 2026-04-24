package orangeHRM.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import orangeHRM.driver.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);
    public static WebDriver driver;

    @Before
    public void setup(Scenario scenario) {
        log.info("======= STARTING SCENARIO: {} =======", scenario.getName());
        driver = DriverManager.getDriver();
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("SCENARIO FAILED: {}", scenario.getName());
            if (DriverManager.getDriver() != null) {
                log.info("Capturing failure screenshot for Allure report...");
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failed Step Screenshot", new ByteArrayInputStream(screenshot));
            }
        } else {
            log.info("SCENARIO PASSED: {}", scenario.getName());
        }

        log.info("Cleaning up driver session.");
        DriverManager.quitDriver();
        log.info("======= FINISHED SCENARIO: {} =======", scenario.getName());
    }
}