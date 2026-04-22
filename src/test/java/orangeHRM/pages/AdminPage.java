package orangeHRM.pages;

import com.fasterxml.jackson.databind.ser.Serializers;
import orangeHRM.base.BaseElement;
import orangeHRM.base.BasePage;
import orangeHRM.pages.components.UserTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class AdminPage extends BasePage {
    protected BaseElement usernameInput;
    protected BaseElement searchButton;
    protected BaseElement resetButton;
    protected BaseElement recordsNumber;
    protected BaseElement userRoleDropdown;
    protected BaseElement employeeNameInput;
    protected BaseElement statusDropdown;
    protected BaseElement addButton;
    public AdminPage(WebDriver driver) {
        super(driver, "Admin page");;
        usernameInput = new BaseElement(driver, "Username input field on " + this.getName(), By.xpath("//label[normalize-space()='Username']/parent::div/following-sibling::*//input"));
        searchButton = new BaseElement(driver, "Search button on " + this.getName(), By.xpath("//button[normalize-space()='Search']"));
        resetButton = new BaseElement(driver, "Reset button on " + this.getName(), By.xpath("//button[normalize-space()='Reset']"));
        recordsNumber = new BaseElement(driver, "Records number on " + this.getName(), By.xpath("//span[contains(normalize-space(), 'Record')]"));
        userRoleDropdown = new BaseElement(driver, "User role dropdown on " + this.getName(), By.xpath("//label[normalize-space()='User Role']/parent::div/following-sibling::div"));
        employeeNameInput = new BaseElement(driver, "Employee name input field on " + this.getName(), By.xpath("//label[normalize-space()='Employee Name']/parent::div/following-sibling::*//input"));
        statusDropdown = new BaseElement(driver, "Status dropdown on " + this.getName(), By.xpath("//label[normalize-space()='Status']/parent::div/following-sibling::div"));
        addButton = new BaseElement(driver, "Reset button on " + this.getName(), By.xpath("//button[normalize-space()='Add']"));
    }

    public void enterUsername(String username){
        usernameInput.typeText(username);
    }

    public void clickSearch(){
        searchButton.click();
    }

    public void clickReset(){
        resetButton.click();
    }

    public int getNumberOfRecords()
    {
        String text = recordsNumber.getText();

        if (text.equals("No Records Found")) {
            return 0;
        }

        String numberOnly = text.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberOnly);
    }

    public List<UserTableRow> getAllUserRows() {
        List<WebElement> rowElements = driver.findElements(By.className("oxd-table-card"));

        return rowElements.stream()
                .map(UserTableRow::new)
                .collect(Collectors.toList());
    }

    public void selectUserRole(String role)
    {
        userRoleDropdown.click();
        BaseElement roleOption = new BaseElement(driver, role + " option of " + userRoleDropdown.getName(), By.xpath("//*[@role='option' and normalize-space()='%s']".formatted(role)));
        roleOption.click();
    }

    public void enterEmployeeName(String employeeName){
        employeeNameInput.typeText(employeeName);
    }

    public void selectEmployeeName(String employeeName){
        employeeNameInput.typeText(employeeName);
        BaseElement employeeOption = new BaseElement(driver, employeeName + " option of " + employeeNameInput.getName(), By.xpath("//div[@role='option'][1]"));
        employeeOption.click();
    }

    public void assertValidationUnderEmployeeNameInputIsVisible(){
        BaseElement validation = new BaseElement(driver, "Validation message under " + employeeNameInput.getName(), By.xpath("//label[normalize-space()='Employee Name']/parent::div/following-sibling::span"));
        validation.assertTextIs("Invalid");
        validation.assertIsDisplayed();
    }

    public void selectStatus(String status)
    {
        statusDropdown.click();
        BaseElement statusOption = new BaseElement(driver, status + " option of " + userRoleDropdown.getName(), By.xpath("//*[@role='option' and normalize-space()='%s']".formatted(status)));
        statusOption.click();
    }

    public String  getUsernameInputValue(){
        return usernameInput.getText();
    }

    public String  getUserRoleDropwdownValue(){
        return userRoleDropdown.getText();
    }

    public String  getEmployeeNameInputValue(){
        return employeeNameInput.getText();
    }

    public String  getStatusDropdownValue(){
        return statusDropdown.getText();
    }

    public AddUserPage clickAddButton(){
        addButton.click();
        return new AddUserPage(driver);
    }
}
