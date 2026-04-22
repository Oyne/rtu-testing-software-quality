package orangeHRM.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import orangeHRM.hooks.Hooks;
import orangeHRM.pages.*;
import orangeHRM.pages.components.UserTableRow;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminPageSteps {
    private AdminPage adminPage;
    private AddUserPage addUserPage;
    private List<UserTableRow> records;
    private String deletedUsername;
    private String savedEmployeeName;
    private String newUserUsername;
    private final String newUserRole = "ESS";
    private final String newUserStatus = "Enabled";
    private final String newUserPassword = "TestPassword9RTU!";
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

        if (!records.isEmpty()) {
            assertThat(records)
                    .as("Checking that all returned users have the employee name: " + savedEmployeeName)
                    .allSatisfy(record -> {
                        assertThat(record.getEmployeeName())
                                .isEqualTo(savedEmployeeName);
                    });
        }
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

    @Given("the users open add user page")
    public void theUsersOpenAddUserPage() {
        addUserPage = adminPage.clickAddButton();
    }

    @Then("admin page opened")
    public void verifyOpened() {
        assertThat(Hooks.driver.getCurrentUrl())
                .as("Admin Tab URL")
                .contains("/admin/viewSystemUsers");
    }

    @When("the user attempts to delete first record of non admin user")
    public void theUserAttemptsToDeleteFirstRecordOfNonAdminUser() {
        records = adminPage.getAllUserRows();
        UserTableRow targetRow = records.stream()
                .filter(record -> !record.getUserRole().equalsIgnoreCase("Admin"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Could not find a non-admin user to delete."));

        deletedUsername = targetRow.getUsername();

        targetRow.clickDelete();
        Modal modal = new Modal(Hooks.driver);
        modal.clickOnButtonByName("Yes, Delete");
    }

    @Then("deleted user should not be displayed")
    public void deletedUserShouldNotBeDisplayed() {
        records = adminPage.getAllUserRows();

        assertThat(records)
                .as("Check that username '" + deletedUsername + "' was removed")
                .extracting(UserTableRow::getUsername)
                .doesNotContain(deletedUsername);

        log.info("Verified: User {} is no longer in the table.", deletedUsername);
    }

    @Given("the user clicks cancel")
    public void cancel() {
        addUserPage.clickCancel();
    }

    @When("the user attempts creating new user with required valid data")
    public void theUserAttemptsCreatingNewUserWithRequiredValidData() {
        newUserUsername = "TestUser" + System.currentTimeMillis();
        addUserPage.enterUsername(newUserUsername);
        addUserPage.selectEmployeeName(savedEmployeeName);
        addUserPage.selectUserRole(newUserRole);
        addUserPage.selectStatus(newUserStatus);
        addUserPage.enterPassword(newUserPassword);
        addUserPage.enterConfirmPassword(newUserPassword);

        addUserPage.clickSave();
    }

    @Then("the new user should be searchable in the system")
    public void theNewUserShouldBeSearchableInTheSystem() {
        adminPage.enterUsername(newUserUsername);
        adminPage.selectUserRole(newUserRole);
        adminPage.selectStatus(newUserStatus);
        adminPage.clickSearch();

        records = adminPage.getAllUserRows();
        UserTableRow actualUser = records.getFirst();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualUser.getUsername())
                .as("Username")
                .isEqualTo(newUserUsername);

        softly.assertThat(actualUser.getUserRole())
                .as("User Role")
                .isEqualTo(newUserRole);

        softly.assertThat(actualUser.getEmployeeName())
                .as("Employee Name")
                .isEqualTo(savedEmployeeName);

        softly.assertThat(actualUser.getStatus())
                .as("Status")
                .isEqualTo(newUserStatus);

        softly.assertAll();
    }
}
