package orangeHRM.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import orangeHRM.driver.DriverManager;

import java.io.ByteArrayInputStream;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
        driver = DriverManager.getDriver();
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed() && DriverManager.getDriver() != null) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failed Step Screenshot", new ByteArrayInputStream(screenshot));
        }

        DriverManager.quitDriver();
    }
}
