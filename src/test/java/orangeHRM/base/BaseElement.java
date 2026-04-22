package orangeHRM.base;

import io.qameta.allure.Allure;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import orangeHRM.utils.CssUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Objects;

public class BaseElement {
    private static final Logger log = LoggerFactory.getLogger(BaseElement.class);
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected By locator;
    private final String name;

    public BaseElement(WebDriver driver, String name, By locator) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.name = name;
        this.locator = locator;
    }

    public String getName() {
        return name;
    }

    protected WebElement find() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void typeText(String text) {
        Allure.step("Type '" + text + "' into " + name, () -> {
            log.info("Typing '{}' into field: {}", text, name);
            var field = find();
            field.clear();
            field.sendKeys(text);
        });
    }

    public void click() {
        Allure.step("Click " + name, () -> {
            log.info("Clicking element: {}", name);
            find().click();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(d -> Objects.equals(((JavascriptExecutor) d)
                            .executeScript("return document.readyState"), "complete"));
        });

        loadingWaiter();
    }

    private void loadingWaiter() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        } catch (Exception ignored) {
        }
    }

    public void assertIsDisplayed() {
        Allure.step("Verify " + name + " is displayed", () -> {
            log.info("Verifying visibility of: {}", name);
            SoftAssertions softAssertions = new SoftAssertions();
            boolean isDisplayed = !driver.findElements(locator).isEmpty() && driver.findElement(locator).isDisplayed();

            softAssertions.assertThat(isDisplayed)
                    .describedAs(name + " should be displayed")
                    .isTrue();
            softAssertions.assertAll();
        });
    }

    public void assertIsNotDisplayed() {
        Allure.step("Verify " + name + " is NOT displayed", () -> {
            log.info("Verifying element is NOT displayed: {}", name);
            SoftAssertions softAssertions = new SoftAssertions();
            boolean isDisplayed = !driver.findElements(locator).isEmpty() && driver.findElement(locator).isDisplayed();

            softAssertions.assertThat(isDisplayed)
                    .describedAs(name + " should not be displayed")
                    .isFalse();
            softAssertions.assertAll();
        });
    }

    public void assertAttributeValue(String attribute, String expectedValue) {
        Allure.step("Verify " + name + " attribute '" + attribute + "' is '" + expectedValue + "'", () -> {
            var actualValue = find().getAttribute(attribute);
            log.info("Verifying attribute '{}' for {}: Expected '{}', Got '{}'", attribute, name, expectedValue, actualValue);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(actualValue)
                    .describedAs(name + " attribute [" + attribute + "]")
                    .isEqualTo(expectedValue);
            softAssertions.assertAll();
        });
    }

    public void assertCssColor(String property, String expectedColor) {
        Allure.step("Verify " + name + " CSS '" + property + "' is '" + expectedColor + "'", () -> {
            String actualColor = CssUtils.normalizeColor(find().getCssValue(property));
            log.info("Verifying CSS property '{}' for {}: Expected '{}', Got '{}'", property, name, expectedColor, actualColor);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(actualColor)
                    .describedAs(name + " " + property + " color")
                    .isEqualTo(expectedColor);
            softAssertions.assertAll();
        });
    }

    public void assertTextIs(String expectedText) {
        Allure.step("Verify " + name + " text is '" + expectedText + "'", () -> {
            var actualText = find().getText();
            log.info("Verifying text for {}: Expected '{}', Got '{}'", name, expectedText, actualText);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(actualText)
                    .describedAs(name + " text content")
                    .isEqualTo(expectedText);
            softAssertions.assertAll();
        });
    }

    public String getText()
    {
        return find().getText();
    }
}