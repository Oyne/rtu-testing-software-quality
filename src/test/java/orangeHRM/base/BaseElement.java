package orangeHRM.base;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import orangeHRM.utils.CssUtils;

import java.time.Duration;

public class BaseElement {
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
        var field = find();
        field.clear();
        field.sendKeys(text);
    }

    public void click() {
        find().click();
    }

    public void assertIsNotDisplayed() {
        SoftAssertions softAssertions = new SoftAssertions();
        boolean isDisplayed = !driver.findElements(locator).isEmpty() && driver.findElement(locator).isDisplayed();
        softAssertions.assertThat(isDisplayed)
                .describedAs(name + " should not be displayed")
                .isFalse();
        softAssertions.assertAll();
    }


    public void assertIsClickable() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(find().isEnabled())
                .describedAs(name)
                .isTrue();
        softAssertions.assertAll();
    }

    public void assertAttributeValue(String attribute, String expectedValue) {
        SoftAssertions softAssertions = new SoftAssertions();
        var attributeValue = find().getAttribute(attribute);
        softAssertions.assertThat(attributeValue)
                .describedAs(name + " " + attribute)
                .isEqualTo(expectedValue);
        softAssertions.assertAll();
    }

    public void assertCssColor(String property, String expectedColor) {
        String actualColor = CssUtils.normalizeColor(find().getCssValue(property));
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualColor)
                .describedAs(name + " " + property + " " + "color")
                .isEqualTo(expectedColor);
        softAssertions.assertAll();
    }

    public void assertTextIs(String expectedText) {
        SoftAssertions softAssertions = new SoftAssertions();
        var textValue = find().getText();
        softAssertions.assertThat(textValue)
                .describedAs(name + " Text")
                .isEqualTo(expectedText);
        softAssertions.assertAll();
    }
}
