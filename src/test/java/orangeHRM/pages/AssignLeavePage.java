package orangeHRM.pages;

import orangeHRM.base.BaseElement;
import orangeHRM.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AssignLeavePage extends BasePage {
    protected BaseElement employeeNameInput;
    protected BaseElement leaveTypeDropdown;
    protected BaseElement fromDateInput;
    protected BaseElement toDateInput;
    protected BaseElement commentsInput;
    protected BaseElement assignButton;

    public AssignLeavePage(WebDriver driver) {
        super(driver, "Assign Leave page");
        assignButton = new BaseElement(driver, "Assign button on " + this.getName(), By.xpath("//button[normalize-space()='Assign']"));
        employeeNameInput = new BaseElement(driver, "Employee name input field on " + this.getName(), By.xpath("//label[normalize-space()='Employee Name']/parent::div/following-sibling::*//input"));
        leaveTypeDropdown = new BaseElement(driver, "Leave type dropdown on " + this.getName(), By.xpath("//label[normalize-space()='Leave Type']/parent::div/following-sibling::div"));
        fromDateInput = new BaseElement(driver, "From date input field on " + this.getName(), By.xpath("//label[normalize-space()='From Date']/parent::div/following-sibling::*//input"));
        toDateInput = new BaseElement(driver, "To date input field on " + this.getName(), By.xpath("//label[normalize-space()='To Date']/parent::div/following-sibling::*//input"));
        commentsInput = new BaseElement(driver, "Comments area on " + this.getName(), By.xpath("//label[normalize-space()='Comments']/parent::div/following-sibling::div//textarea"));
    }

    private void assertFieldValidation(String labelName, String expectedMessage) {
        String xpath = "//label[normalize-space()='%s']/parent::div/following-sibling::span".formatted(labelName);
        BaseElement validation = new BaseElement(driver, "Validation message under " + labelName, By.xpath(xpath));

        if (expectedMessage != null && !expectedMessage.isBlank()) {
            validation.assertIsDisplayed();
            validation.assertTextIs(expectedMessage);
        }
        else {
            validation.assertIsNotDisplayed();
        }
    }

    public void assertEmployeeNameValidation(String message) {
        assertFieldValidation("Employee Name", message);
    }

    public void assertLeaveTypeValidation(String message) {
        assertFieldValidation("Leave Type", message);
    }

    public void assertFromDateValidation(String message) {
        assertFieldValidation("From Date", message);
    }

    public void assertToDateValidation(String message) {
        assertFieldValidation("To Date", message);
    }

    public void assertCommentsValidation(String message) {
        assertFieldValidation("Comments", message);
    }

    public Modal clickAssign(){
        assignButton.click();
        return new Modal(driver);
    }

    public String selectEmployeeName(String employeeName){
        employeeNameInput.typeText(employeeName);
        BaseElement employeeOption = new BaseElement(driver, employeeName + " option of " + employeeNameInput.getName(), By.xpath("//div[@role='option'][1]"));
        var fullName = employeeOption.getText();
        employeeOption.click();
        return fullName;
    }

    public void enterFromDate(String date){
        fromDateInput.typeText(date);
    }

    public void enterToDate(String date){
        toDateInput.typeText(date);
    }

    public void selectLeaveType(String type)
    {
        leaveTypeDropdown.click();
        BaseElement leaveTypeOption = new BaseElement(driver, type + " option of " + leaveTypeDropdown.getName(), By.xpath("//*[@role='option' and normalize-space()='%s']".formatted(type)));
        leaveTypeOption.click();
    }
}
