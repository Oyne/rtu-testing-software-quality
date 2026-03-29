package orangeHRM.steps;

import orangeHRM.hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import orangeHRM.pages.components.Header;
import orangeHRM.pages.components.LoginModal;

public class LoginSteps {

    private LoginModal loginModal;

    @Given("the user opens the Login modal")
    public void openLoginModal() {
        Header header = new Header(Hooks.driver);
        loginModal = header.clickOnLogin();
    }

    @Then("the Login modal title should be {string}")
    public void verifyLoginModalTitle(String expectedTitle) {
        loginModal.assertTitleTextIs(expectedTitle);
    }

    @When("the user enters email {string}")
    public void enterEmail(String email) {
        loginModal.inputPhoneOrEmail(email);
    }

    @And("the Phone or email input placeholder should be {string}")
    public void verifyEmailOrPhoneInputPlaceholder(String expectedPlaceholder) {
        loginModal.assertPhoneOrEmailFieldPlaceholderIs(expectedPlaceholder);
    }

    @And("the Phone or email input should be highlighted")
    public void verifyPhoneOrEmailInputIsHighlighted() {
        loginModal.assertPhoneOrEmailFieldHighlightIsVisible();
    }

    @When("the user enters password {string}")
    public void enterPassword(String password) {
        loginModal.inputPassword(password);
    }

    @And("the Password input placeholder should be {string}")
    public void verifyPasswordInputPlaceholderIs(String expectedPlaceholder) {
        loginModal.assertPasswordFieldPlaceholderIs(expectedPlaceholder);
    }

    @And("the show|hide password button should be clickable")
    public void verifyShowHidePasswordButtonIsClickable() {
        loginModal.assertShowHidePasswordButtonIsClickable();
    }

    @And("the Password input should be highlighted")
    public void verifyPasswordInputIsHighlighted() {
        loginModal.assertPasswordFieldHighlightIsVisible();
    }

    @And("the Remember Me checkbox should be clickable")
    public void verifyRememberMeCheckboxIsClickable() {
        loginModal.assertRememberMeCheckboxIsClickable();
    }

    @And("the Remember Me checkbox label should be {string}")
    public void verifyRememberMeCheckboxLabel(String expectedLabel) {
        loginModal.assertRememberMeCheckboxTextIs(expectedLabel);
    }

    @And("the Forgot Password link should be clickable")
    public void verifyForgotPasswordLinkIsClickable() {
        loginModal.assertForgotPasswordLinkIsClickable();
    }

    @And("the Forgot Password link text should be {string}")
    public void verifyForgotPasswordLinkTextIs(String expectedText) {
        loginModal.assertForgotPasswordLinkTextIs(expectedText);
    }

    @When("the user clicks the Login button")
    public void clickLoginButton() {
        loginModal.clickOnLoginButton();
    }

    @And("the Login button should be clickable")
    public void verifyLoginButtonIsClickable() {
        loginModal.assertLoginButtonIsClickable();
    }

    @And("the Login button text should be {string}")
    public void verifyLoginButtonTextIs(String expectedText) {
        loginModal.assertLoginButtonTextIs(expectedText);
    }

    @And("all social login buttons should be clickable")
    public void verifyAllSocialLoginButtonsShouldBeClickable() {
        loginModal.assertLoginWithFacebookIsClickable();
        loginModal.assertLoginWithGoogleIsClickable();
        loginModal.assertLoginWithAppleIsClickable();
    }

    @When("the user closes the login modal")
    public void theUserClosesTheLoginModal() {
        loginModal.clickOnCloseIcon();
    }

    @Then("the Login modal should be closed")
    public void verifyLoginModalIsClosed() {
        loginModal.assertTitleIsNotVisible();
    }

    @Then("the validation message should be {string}")
    public void verifyValidationMessageTextIs(String expectedText) {
        loginModal.assertValidationErrorTextIs(expectedText);
        loginModal.assertValidationErrorTextColor();
    }
}
