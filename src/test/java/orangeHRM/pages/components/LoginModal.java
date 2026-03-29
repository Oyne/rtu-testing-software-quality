package orangeHRM.pages.components;

import orangeHRM.base.BaseElement;
import orangeHRM.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;

public class LoginModal extends BasePage {

    private final String validationErrorColor = Color.fromString("#FF6660").asRgb();
    private final String borderBottomColorProperty = "border-bottom-color";
    protected BaseElement title;
    protected BaseElement phoneOrEmailField;
    protected BaseElement passwordField;
    protected BaseElement showHidePasswordButton;
    protected BaseElement rememberMeCheckbox;
    protected BaseElement forgotPasswordLink;
    protected BaseElement loginButton;
    protected BaseElement facebookButton;
    protected BaseElement googleButton;
    protected BaseElement appleButton;
    protected BaseElement validationError;
    protected BaseElement closeIcon;

    public LoginModal(WebDriver driver) {
        super(driver);
        title = new BaseElement(driver, "Login modal title", By.className("mw_header"));
        phoneOrEmailField = new BaseElement(driver, "Login modal phone/email field", By.id("lf-login"));
        passwordField = new BaseElement(driver, "Login modal password field", By.id("lf-password"));
        showHidePasswordButton = new BaseElement(driver, "Login modal show/hide password button", By.className("pass-show"));
        rememberMeCheckbox = new BaseElement(driver, "Login modal remember me checkbox", By.className("lf-lbl"));
        forgotPasswordLink = new BaseElement(driver, "Login modal forgot password link", By.className("lf-a"));
        loginButton = new BaseElement(driver, "Login modal login button", By.className("mw-submit"));
        facebookButton = new BaseElement(driver, "Login modal login with Facebook", By.xpath("//*[@data-network='fb']"));
        googleButton = new BaseElement(driver, "Login modal login with Google", By.xpath("//*[@data-network='google']"));
        appleButton = new BaseElement(driver, "Login modal login with Apple", By.xpath("//*[@data-network='apple']"));
        validationError = new BaseElement(driver, "Login modal validation error", By.className("mw_error_text"));
        closeIcon = new BaseElement(driver, "Login modal close ICON", By.className("mw-close"));
    }

    @Step("Assert that login modal title is not visible")
    public void assertTitleIsNotVisible() {
        title.assertIsNotDisplayed();
    }

    @Step("Assert that login modal title is '{expectedText}'")
    public void assertTitleTextIs(String expectedText) {
        title.assertTextIs(expectedText);
    }


    @Step("Assert that login modal phone or email input placeholder is '{expectedPlaceholder}'")
    public void assertPhoneOrEmailFieldPlaceholderIs(String expectedPlaceholder) {
        phoneOrEmailField.assertAttributeValue("placeholder", expectedPlaceholder);
    }

    @Step("Assert that login modal phone or email input is highlighted")
    public void assertPhoneOrEmailFieldHighlightIsVisible() {
        phoneOrEmailField.assertCssColor(borderBottomColorProperty, validationErrorColor);
    }

    @Step("Type {phoneOrEmail} in phone or email input on login modal")
    public void inputPhoneOrEmail(String phoneOrEmail) {
        phoneOrEmailField.typeText(phoneOrEmail);
    }


    @Step("Assert that login modal password input placeholder is '{expectedPlaceholder}'")
    public void assertPasswordFieldPlaceholderIs(String expectedPlaceholder) {
        passwordField.assertAttributeValue("placeholder", expectedPlaceholder);
    }

    @Step("Assert that login modal password input is highlighted")
    public void assertPasswordFieldHighlightIsVisible() {
        passwordField.assertCssColor(borderBottomColorProperty, validationErrorColor);
    }

    @Step("Type password in password input on login modal")
    public void inputPassword(String password) {
        passwordField.typeText(password);
    }

    @Step("Assert that show/hide password icon is visible on login modal password input")
    public void assertShowHidePasswordButtonIsClickable() {
        showHidePasswordButton.assertIsClickable();
    }

    @Step("Asser that remember me checkbox is clickable on login modal")
    public void assertRememberMeCheckboxIsClickable() {
        rememberMeCheckbox.assertIsClickable();
    }

    @Step("Asser that remember me checkbox label is '{expectedText}' on login modal")
    public void assertRememberMeCheckboxTextIs(String expectedText) {
        rememberMeCheckbox.assertTextIs(expectedText);
    }

    @Step("Asser that forgot password link is clickable on login modal")
    public void assertForgotPasswordLinkIsClickable() {
        forgotPasswordLink.assertIsClickable();
    }

    @Step("Asser that forgot password link text is '{expectedText}' on login modal")
    public void assertForgotPasswordLinkTextIs(String expectedText) {
        forgotPasswordLink.assertTextIs(expectedText);
    }

    @Step("Asser that login button is clickable on login modal")
    public void assertLoginButtonIsClickable() {
        loginButton.assertIsClickable();
    }

    @Step("Asser that login button text is '{expectedText}' on login modal")
    public void assertLoginButtonTextIs(String expectedText) {
        loginButton.assertTextIs(expectedText);
    }

    @Step("Click on login button on login modal")
    public void clickOnLoginButton() {
        loginButton.click();
    }

    @Step("Assert that login with Facebook link is clickable on login modal")
    public void assertLoginWithFacebookIsClickable() {
        facebookButton.assertIsClickable();
    }

    @Step("Assert that login with Google link is clickable on login modal")
    public void assertLoginWithGoogleIsClickable() {
        googleButton.assertIsClickable();
    }

    @Step("Assert that login with Apple link is clickable on login modal")
    public void assertLoginWithAppleIsClickable() {
        appleButton.assertIsClickable();
    }

    @Step("Assert that validation error message is '{expectedText}' on login modal")
    public void assertValidationErrorTextIs(String expectedText) {
        validationError.assertTextIs(expectedText);
    }

    @Step("Assert validation error message color")
    public void assertValidationErrorTextColor() {
        String colorProperty = "color";
        validationError.assertCssColor(colorProperty, validationErrorColor);
    }

    @Step("Click on X(close button) on login modal")
    public void clickOnCloseIcon(){
        closeIcon.click();
    }
}