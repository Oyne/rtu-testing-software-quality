package orangeHRM.steps;

import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import orangeHRM.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import orangeHRM.pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageSteps {

    private static final Logger log = LoggerFactory.getLogger(LoginPageSteps.class);
    private LoginPage loginPage;

    @Given("the user opens login page")
    public void openLoginPage() {
        loginPage = new LoginPage(Hooks.driver);
    }

    @Step("User attempts to log in without credentials")
    @When("the user attempts to log in without credentials")
    public void loginWithoutCredentials() {
        loginPage.clickLogin();
    }

    @Step("User attempts to log in with only a username")
    @When("the user attempts to log in with only a username")
    public void loginWithOnlyUsername() {
        loginPage.enterUsername("test");
        loginPage.clickLogin();
    }

    @Step("User attempts to log in with only a password")
    @When("the user attempts to log in with only a password")
    public void loginWithOnlyPassword() {
        loginPage.enterPassword("test");
        loginPage.clickLogin();
    }

    @Step("User attempts to log in with invalid credentials")
    @When("the user attempts to log in with invalid credentials")
    public void loginWithInvalidCredentials() {
        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword("invalidPass");
        loginPage.clickLogin();
    }

    @Step("User logs in with valid credentials")
    @When("the user logs in with valid credentials")
    public void loginWithValidCredentials() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
    }

    @Then("username validation message {string} appears")
    public void assertUsernameValidation(String message) {
        loginPage.assertUsernameFieldValidationIsVisible(message);
    }

    @Then("password validation message {string} appears")
    public void assertPasswordValidation(String message) {
        loginPage.assertPasswordFieldValidationIsVisible(message);
    }

    @Then("current page is still login page")
    public void verifyPage() {
        assertThat(Hooks.driver.getCurrentUrl())
                .as("Current URL")
                .isEqualTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Then("{string} error is visible")
    public void verifyError(String error)
    {
        loginPage.assertErrorIsVisible(error);
    }
}
