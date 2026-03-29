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

    @Step("User enters username '{username}'")
    @When("the user enters username {string}")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @Step("User enters password '{password}'")
    @When("the user enters password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @Step("User presses login")
    @When("the user presses login button")
    public void pressLogin() {
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
