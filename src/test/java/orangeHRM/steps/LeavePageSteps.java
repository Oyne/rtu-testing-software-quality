package orangeHRM.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import orangeHRM.hooks.Hooks;
import orangeHRM.pages.*;
import orangeHRM.pages.components.LeaveTableRow;
import orangeHRM.pages.components.UserTableRow;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class LeavePageSteps {
    private AdminPage adminPage;
    private LeavePage leavePage;
    private AssignLeavePage assignLeavePage;
    private String savedEmployeeName;

    @Given("the user logged in and opened leave tab after capturing the employee name of the first record in the table of admin tab")
    public void openLeaveTab() {
        LoginPage loginPage = new LoginPage(Hooks.driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        SideBar sideBar = new SideBar(Hooks.driver);
        adminPage = sideBar.clickOnAdminTab();
        var records = adminPage.getAllUserRows();
        savedEmployeeName = records.getFirst().getEmployeeName();
        leavePage = sideBar.clickOnLeaveTab();
    }

    @Given("the users open assign leave page")
    public void theUsersOpenAssignLeavePage() {
        assignLeavePage = leavePage.clickAssignLeaveTab();
    }

    @Then("validation messages appear after each field of assign leave tab")
    public void validationMessagesAppearAfterEachField() {
        assignLeavePage.assertEmployeeNameValidation("Required");
        assignLeavePage.assertLeaveTypeValidation("Required");
        assignLeavePage.assertFromDateValidation("Required");
        assignLeavePage.assertToDateValidation("Required");
        assignLeavePage.assertCommentsValidation("");
    }

    @When("the user attempts to save new leave without required data")
    public void theUserAttemptsToSaveNewLeaveWithoutRequiredData() {
        assignLeavePage.clickAssign();
    }

    @When("the user attempts to add a leave to captured employee")
    public void theUserAttemptsToAddALeaveToCapturedEmployee() {
        assignLeavePage = leavePage.clickAssignLeaveTab();
        savedEmployeeName = assignLeavePage.selectEmployeeName(savedEmployeeName);
        assignLeavePage.selectLeaveType("CAN - Vacation");
        assignLeavePage.enterFromDate("2026-30-04");
        assignLeavePage.enterToDate("2026-30-04");
        Modal modal = assignLeavePage.clickAssign();
        modal.clickOnButtonByName("Ok");
        leavePage.clickLeaveListTab();
    }

    @Then("created leave should be visible in leave list")
    public void createdLeaveShouldBeVisibleInLeaveList() {
        leavePage.selectEmployeeName(savedEmployeeName);
        leavePage.selectLeaveType("CAN - Vacation");
        leavePage.enterFromDate("2026-30-04");
        leavePage.enterToDate("2026-30-04");
        leavePage.selectShowLeaveWithStatus("Scheduled");
        leavePage.clickSearch();

        List<LeaveTableRow> records = leavePage.getAllLeaveRows();

        assertThat(records)
                .as("Leave list search results")
                .isNotEmpty();

        LeaveTableRow actualLeave = records.getFirst();
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(actualLeave.getDate())
                .as("Leave Date")
                .isEqualTo("2026-30-04");

        // We use contains because the table often adds the Employee ID
        softly.assertThat(actualLeave.getEmployeeName())
                .as("Employee Name")
                .contains(savedEmployeeName);

        softly.assertThat(actualLeave.getLeaveType())
                .as("Leave Type")
                .isEqualTo("CAN - Vacation");

        softly.assertThat(actualLeave.getLeaveBalance())
                .as("Leave Balance")
                .isEqualTo("-1.00");

        softly.assertThat(actualLeave.getNumberOfDays())
                .as("Number of Days")
                .isEqualTo("1.00");

        softly.assertThat(actualLeave.getStatus())
                .as("Leave Status")
                .isEqualTo("Scheduled (1.00)");

        softly.assertAll();
    }

    @Given("the leave for captured employee exists")
    public void theLeaveForCapturedEmployeeExists() {
        leavePage.clickLeaveListTab();
        leavePage.selectEmployeeName(savedEmployeeName);
        leavePage.selectLeaveType("CAN - Vacation");
        leavePage.enterFromDate("2026-30-04");
        leavePage.enterToDate("2026-30-04");
        leavePage.selectShowLeaveWithStatus("Scheduled");
        leavePage.clickSearch();

        List<LeaveTableRow> records = leavePage.getAllLeaveRows();

        if (records.isEmpty()) {
            assignLeavePage = leavePage.clickAssignLeaveTab();
            savedEmployeeName = assignLeavePage.selectEmployeeName(savedEmployeeName);
            assignLeavePage.selectLeaveType("CAN - Vacation");
            assignLeavePage.enterFromDate("2026-30-04");
            assignLeavePage.enterToDate("2026-30-04");

            Modal modal = assignLeavePage.clickAssign();
            modal.clickOnButtonByName("Ok");

            leavePage.clickLeaveListTab();
            leavePage.selectEmployeeName(savedEmployeeName);
            leavePage.selectLeaveType("CAN - Vacation");
            leavePage.enterFromDate("2026-30-04");
            leavePage.enterToDate("2026-30-04");
            leavePage.selectShowLeaveWithStatus("Scheduled");
            leavePage.clickSearch();
        }
    }

    @When("the user attempts deleting existing leave")
    public void theUserAttemptsDeletingExistingLeave() {
        List<LeaveTableRow> records = leavePage.getAllLeaveRows();
        LeaveTableRow targetLeave = records.getFirst();
        leavePage.kebabOption(targetLeave.getEmployeeName(), "Cancel Leave");
    }

    @Then("deleted leave should be not visible in leave list")
    public void deletedLeaveShouldBeNotVisibleInLeaveList() {
        leavePage.clickSearch();

        List<LeaveTableRow> records = leavePage.getAllLeaveRows();

        assertThat(records)
                .as("Leave list should be empty after deleting/cancelling the scheduled leave")
                .isEmpty();
    }
}
