package orangeHRM.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import orangeHRM.hooks.Hooks;
import orangeHRM.pages.AdminPage;
import orangeHRM.pages.LoginPage;
import orangeHRM.pages.SideBar;
import orangeHRM.pages.components.UserTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminPageSteps {
    private AdminPage adminPage;
    private List<UserTableRow> records;
    private List<UserTableRow> initRecords;
    private String savedEmployeeName;
    private static final Logger log = LoggerFactory.getLogger(AdminPageSteps.class);

    @Given("the user logged in and opened admin tab")
    public void openAdminTab() {
        LoginPage loginPage = new LoginPage(Hooks.driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        SideBar sideBar = new SideBar(Hooks.driver);
        sideBar.clickOnAdminTab();
        adminPage = sideBar.clickOnAdminTab();
        initRecords = adminPage.getAllUserRows();
    }

    @When("the user searches users by part of username {string}")
    @When("the user attempts to search with full username {string}")
    public void searchByUsername(String username) {
        adminPage.enterUsername(username);
        adminPage.clickSearch();
    }

    @Then("No Records Found")
    public void noRecordsFoundIsVisible() {
        int numberOfRecords = adminPage.getNumberOfRecords();
        assertThat(numberOfRecords)
                .as("Number of records")
                .isEqualTo(0);
    }

    @Then("user with username {string} is shown")
    public void userWithUsernameIsShown(String expectedUsername) {
        records = adminPage.getAllUserRows();

        assertThat(records.size())
                .as("Number of records found for " + expectedUsername)
                .isEqualTo(1);

        assertThat(records.getFirst().getUsername())
                .as("Username in the first row")
                .isEqualTo(expectedUsername);
    }

    @When("the user attempts to search by {string} user role")
    public void theUserSelectsFromTheUserRoleDropdown(String role) {
        adminPage.selectUserRole(role);
        adminPage.clickSearch();
    }

    @Then("users with only {string} user role are visible")
    public void usersWithUserRoleAreVisible(String expectedRole) {
        var records = adminPage.getAllUserRows();

        if (!records.isEmpty()) {
            assertThat(records)
                    .as("Checking that all returned users have the role: " + expectedRole)
                    .allSatisfy(record -> {
                        assertThat(record.getUserRole())
                                .isEqualTo(expectedRole);
                    });
        }
    }

    @And("correct number of records is shown")
    public void correctNumberOfRecordsIsShown() {
        records = adminPage.getAllUserRows();
        int recordsNumber = adminPage.getNumberOfRecords();
        org.assertj.core.api.Assertions.assertThat(records.size())
                .as("Table row count vs Records found number")
                .isEqualTo(recordsNumber);
    }

    @When("the user attempts to search by a non-existing employee name {string}")
    public void theUserAttemptsToSearchByANonExistingEmployeeName(String employeeName) {
        adminPage.enterEmployeeName(employeeName);
        adminPage.clickSearch();
    }

    @Then("employee name field validation is visible")
    public void employeeNameFieldValidationIsVisible() {
        adminPage.assertValidationUnderEmployeeNameInputIsVisible();
    }

    @When("the user attempts to search by {string} status")
    public void theUserAttemptsToSearchByStatus(String status) {
        adminPage.selectStatus(status);
        adminPage.clickSearch();
    }

    @Then("users with only {string} status are visible")
    public void usersWithStatusAreVisible(String expectedStatus) {
        records = adminPage.getAllUserRows();

        if (!records.isEmpty()) {
            assertThat(records)
                    .as("Checking that all returned users have the status: " + expectedStatus)
                    .allSatisfy(record -> {
                        assertThat(record.getStatus())
                                .isEqualTo(expectedStatus);
                    });
        }
    }

    @When("the user captures the employee name of the first record in the table")
    public void theUserCapturesTheEmployeeNameOfTheFirstRecordInTheTable() {
        records = adminPage.getAllUserRows();
        savedEmployeeName = records.getFirst().getEmployeeName();
    }

    @And("the user searches by the captured employee name")
    public void theUserSearchesByTheCapturedEmployeeName() {
        adminPage.selectEmployeeName(savedEmployeeName);
        adminPage.clickSearch();
    }

    @Then("the table should only show record for that captured name")
    public void theTableShouldOnlyShowRecordsForThatCapturedName() {
        records = adminPage.getAllUserRows();

        assertThat(records.size())
                .as("Number of records found for " + savedEmployeeName)
                .isEqualTo(1);

        assertThat(records.getFirst().getEmployeeName())
                .as("Employee name in the first row")
                .isEqualTo(savedEmployeeName);
    }

    @When("the user filters by {string}, {string} and {string}")
    public void theUserFiltersByAnd(String username, String userRole, String status) {
        if (username != null && !username.isBlank()) {
            adminPage.enterUsername(username);
        }

        if (userRole != null && !userRole.isBlank()) {
            adminPage.selectUserRole(userRole);
        }

        if (status != null && !status.isBlank()) {
            adminPage.selectStatus(status);
        }

        adminPage.clickSearch();
    }

    @Then("the results should match the filtered criteria of {string}, {string} and {string}")
    public void theResultsShouldMatchTheFilteredCriteriaOfAnd(String expectedUsername, String expectedRole, String expectedStatus) {
        records = adminPage.getAllUserRows();

        if (!records.isEmpty()) {
            assertThat(records)
                    .as("Checking that all returned records match the filtering parameters of username: %s, user role: %s, status: %s".formatted(expectedUsername, expectedRole, expectedStatus))
                    .allSatisfy(record -> {
                        if (expectedUsername != null && !expectedUsername.isBlank()) {
                            assertThat(record.getUsername())
                                    .as("Username mismatch")
                                    .containsIgnoringCase(expectedUsername);
                        }

                        if (expectedRole != null && !expectedRole.isBlank()) {
                            assertThat(record.getUserRole())
                                    .as("Role mismatch")
                                    .isEqualTo(expectedRole);
                        }

                        if (expectedStatus != null && !expectedStatus.isBlank()) {
                            assertThat(record.getStatus())
                                    .as("Status mismatch")
                                    .isEqualTo(expectedStatus);
                        }
                    });
        }
    }

    @When("the user resets the search filters")
    public void theUserResetsTheSearchFilters() {
        adminPage.clickReset();
    }

    @Then("the search fields should be cleared")
    public void theSearchFieldsShouldBeCleared() {
        assertThat(adminPage.getUsernameInputValue())
                .as("Username field")
                .isEmpty();

        assertThat(adminPage.getUserRoleDropwdownValue())
                .as("User Role dropdown")
                .isEqualTo("-- Select --"); // Or whatever your default placeholder is

        assertThat(adminPage.getEmployeeNameInputValue())
                .as("Employee Name field")
                .isEmpty();

        assertThat(adminPage.getStatusDropdownValue())
                .as("Status dropdown")
                .isEqualTo("-- Select --");
    }

    @And("all system user records should be displayed")
    public void allSystemUserRecordsShouldBeDisplayed() {
        int rowCount = adminPage.getAllUserRows().size();
        int systemCount = adminPage.getNumberOfRecords();

        assertThat(rowCount)
                .as("Table rows vs Records found count")
                .isEqualTo(systemCount);
    }
}
