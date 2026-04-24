package orangeHRM.pages;

import com.beust.ah.A;
import com.fasterxml.jackson.databind.ser.Serializers;
import orangeHRM.base.BaseElement;
import orangeHRM.base.BasePage;
import orangeHRM.pages.components.LeaveTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class LeavePage extends BasePage {
    protected BaseElement assignLeaveTab;
    protected BaseElement leaveListTab;
    protected BaseElement employeeNameInput;
    protected BaseElement leaveTypeDropdown;
    protected BaseElement fromDateInput;
    protected BaseElement toDateInput;
    protected BaseElement showLeaveWithStatusDropdown;
    protected BaseElement searchButton;

    public LeavePage(WebDriver driver) {
        super(driver, "Leave page");
        assignLeaveTab = new BaseElement(driver, "Assign Leave tab of " + this.getName(), By.xpath("//li[normalize-space()='Assign Leave']"));
        leaveListTab = new BaseElement(driver, "Leave list tab of " + this.getName(), By.xpath("//li[normalize-space()='Leave List']"));
        employeeNameInput = new BaseElement(driver, "Employee name input field on " + this.getName(), By.xpath("//label[normalize-space()='Employee Name']/parent::div/following-sibling::*//input"));
        leaveTypeDropdown = new BaseElement(driver, "Leave type dropdown on " + this.getName(), By.xpath("//label[normalize-space()='Leave Type']/parent::div/following-sibling::div"));
        fromDateInput = new BaseElement(driver, "From date input field on " + this.getName(), By.xpath("//label[normalize-space()='From Date']/parent::div/following-sibling::*//input"));
        toDateInput = new BaseElement(driver, "To date input field on " + this.getName(), By.xpath("//label[normalize-space()='To Date']/parent::div/following-sibling::*//input"));
        showLeaveWithStatusDropdown = new BaseElement(driver, "Show leave with status type dropdown on " + this.getName(), By.xpath("//label[normalize-space()='Show Leave with Status']/parent::div/following-sibling::div"));
        searchButton = new BaseElement(driver, "Search button on " + this.getName(), By.xpath("//button[normalize-space()='Search']"));
    }

    public AssignLeavePage clickAssignLeaveTab(){
        assignLeaveTab.click();
        return new AssignLeavePage(driver);
    }

    public void clickLeaveListTab(){
        leaveListTab.click();
    }

    public List<LeaveTableRow> getAllLeaveRows() {
        List<WebElement> rowElements = driver.findElements(By.className("oxd-table-card"));

        return rowElements.stream()
                .map(LeaveTableRow::new)
                .collect(Collectors.toList());
    }

    public void kebabOption(String employeeName, String option) {
        LeaveTableRow leaveRow = getAllLeaveRows().stream()
                .filter(row -> row.getEmployeeName().equalsIgnoreCase(employeeName))
                .toList().getFirst();

        leaveRow.clickKebabMenu();
        BaseElement kebabOption = new BaseElement(driver, option + " of kebab menu of " + employeeName, By.xpath("//ul//li//p[normalize-space()='%s']".formatted(option)));
        kebabOption.click();
    }

    public void selectEmployeeName(String employeeName){
        employeeNameInput.typeText(employeeName);
        BaseElement employeeOption = new BaseElement(driver, employeeName + " option of " + employeeNameInput.getName(), By.xpath("//div[@role='option'][1]"));
        employeeOption.click();
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

    public void selectShowLeaveWithStatus(String status)
    {
        showLeaveWithStatusDropdown.click();
        BaseElement leaveTypeOption = new BaseElement(driver, status + " option of " + showLeaveWithStatusDropdown.getName(), By.xpath("//*[@role='option' and normalize-space()='%s']".formatted(status)));
        leaveTypeOption.click();
    }

    public void clickSearch(){
        searchButton.click();
    }
}
