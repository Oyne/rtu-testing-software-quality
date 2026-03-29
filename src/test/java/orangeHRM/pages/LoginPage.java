package orangeHRM.pages;

import com.fasterxml.jackson.databind.ser.Serializers;
import orangeHRM.base.BaseElement;
import orangeHRM.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private final String fieldXPath = "//input[@name=\"%s\"]";
    private final String username = "username";
    private final String password = "password";
    private final String validationErrorColor = Color.fromString("#eb0910").asRgb();
    protected BaseElement usernameInput;
    protected BaseElement passwordInput;
    protected BaseElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver, "Login page");
        usernameInput = new BaseElement(driver, "Username input field on " + this.getName(), By.xpath(fieldXPath.formatted(username)));
        passwordInput = new BaseElement(driver, "Password input field on " + this.getName(), By.xpath(fieldXPath.formatted(password)));
        loginButton = new BaseElement(driver, "Login button on " + this.getName(), By.xpath("//button[@type=\"submit\"]"));
    }

    public void inputField(BaseElement field, String value) {
        field.typeText(value);
    }

    @Step("Enter username: '{username}'")
    public void enterUsername(String username) {
        inputField(usernameInput, username);
    }

    @Step("Enter password: '{password}'")
    public void enterPassword(String password) {
        inputField(passwordInput, password);
    }

    public void assertFieldValidationIsVisible(BaseElement field, String fieldName, String message) {
        String locator = fieldXPath.formatted(fieldName) + "/parent::div/following-sibling::span";
        BaseElement validation = new BaseElement(driver, field.getName() + " validation message: '%s'".formatted(message), By.xpath(locator));
        field.assertCssColor("border-color", validationErrorColor);
        validation.assertCssColor("color", validationErrorColor);
        validation.assertIsDisplayed();
        validation.assertTextIs(message);
    }

    @Step("Verify username validation message: '{message}'")
    public void assertUsernameFieldValidationIsVisible(String message) {
        assertFieldValidationIsVisible(usernameInput, username, message);
    }

    @Step("Verify password validation message: '{message}'")
    public void assertPasswordFieldValidationIsVisible(String message) {
        assertFieldValidationIsVisible(passwordInput, password, message);
    }

    @Step("Click login button")
    public void clickLogin() {
        loginButton.click();
    }

    @Step("Verify '{error}' error is visible")
    public void assertErrorIsVisible(String errorMessage) {
        BaseElement errorElement = new BaseElement(driver, errorMessage + "error on " + this.getName(), By.cssSelector(".oxd-alert.oxd-alert--error .oxd-alert-content .oxd-alert-content-text"));
        errorElement.assertIsDisplayed();
        errorElement.assertTextIs(errorMessage);
    }
}