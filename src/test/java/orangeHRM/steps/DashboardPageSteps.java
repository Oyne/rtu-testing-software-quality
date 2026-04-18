package orangeHRM.steps;

import io.cucumber.java.en.Then;
import orangeHRM.hooks.Hooks;
import orangeHRM.pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class DashboardPageSteps {

    private static final Logger log = LoggerFactory.getLogger(DashboardPageSteps.class);
    private LoginPage loginPage;

    @Then("dashboard page opened")
    public void verifyOpened() {
        assertThat(Hooks.driver.getCurrentUrl())
                .as("Dashboard URL")
                .contains("/dashboard");
    }
}
