package orangeHRM.steps;

import io.cucumber.java.en.Then;
import orangeHRM.hooks.Hooks;

import static org.assertj.core.api.Assertions.assertThat;

public class DashboardPageSteps {
    @Then("dashboard page opened")
    public void verifyOpened() {
        assertThat(Hooks.driver.getCurrentUrl())
                .as("Dashboard URL")
                .contains("/dashboard");
    }
}
