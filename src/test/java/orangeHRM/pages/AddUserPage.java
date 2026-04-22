package orangeHRM.pages;

import orangeHRM.base.BaseElement;
import orangeHRM.base.BasePage;
import orangeHRM.pages.components.UserTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class AddUserPage extends BasePage {
    protected BaseElement usernameInput;
    protected BaseElement saveButton;
    protected BaseElement cancelButton;
    protected BaseElement userRoleDropdown;
    protected BaseElement employeeNameInput;
    protected BaseElement statusDropdown;
    protected BaseElement passwordInput;
    protected BaseElement confirmPasswordInput;

    public AddUserPage(WebDriver driver) {
        super(driver, "Add User page");;
        usernameInput = new BaseElement(driver, "Username input field on " + this.getName(), By.xpath("//label[normalize-space()='Username']/parent::div/following-sibling::*//input"));
        saveButton = new BaseElement(driver, "Save button on " + this.getName(), By.xpath("//button[normalize-space()='Save']"));
        cancelButton = new BaseElement(driver, "Cancel button on " + this.getName(), By.xpath("//button[normalize-space()='Cancel']"));
        userRoleDropdown = new BaseElement(driver, "User role dropdown on " + this.getName(), By.xpath("//label[normalize-space()='User Role']/parent::div/following-sibling::div"));
        employeeNameInput = new BaseElement(driver, "Employee name input field on " + this.getName(), By.xpath("//label[normalize-space()='Employee Name']/parent::div/following-sibling::*//input"));
        statusDropdown = new BaseElement(driver, "Status dropdown on " + this.getName(), By.xpath("//label[normalize-space()='Status']/parent::div/following-sibling::div"));
        passwordInput = new BaseElement(driver, "Password input field on " + this.getName(), By.xpath("//label[normalize-space()='Password']/parent::div/following-sibling::*//input"));
        confirmPasswordInput = new BaseElement(driver, "Confirm Password input field on " + this.getName(), By.xpath("//label[normalize-space()='Confirm Password']/parent::div/following-sibling::*//input"));

    }

    public void enterUsername(String username){
        usernameInput.typeText(username);
    }

    public void clickSave(){
        saveButton.click();
    }

    public void clickCancel(){
        cancelButton.click();
    }

    public void selectUserRole(String role)
    {
        userRoleDropdown.click();
        BaseElement roleOption = new BaseElement(driver, role + " option of " + userRoleDropdown.getName(), By.xpath("//*[@role='option' and normalize-space()='%s']".formatted(role)));
        roleOption.click();
    }

    public void selectEmployeeName(String employeeName){
        employeeNameInput.typeText(employeeName);
        BaseElement employeeOption = new BaseElement(driver, employeeName + " option of " + employeeNameInput.getName(), By.xpath("//div[@role='option'][1]"));
        employeeOption.click();
    }

    public void selectStatus(String status)
    {
        statusDropdown.click();
        BaseElement statusOption = new BaseElement(driver, status + " option of " + userRoleDropdown.getName(), By.xpath("//*[@role='option' and normalize-space()='%s']".formatted(status)));
        statusOption.click();
    }
    public void enterPassword(String password){
        passwordInput.typeText(password);
    }

    public void enterConfirmPassword(String password){
        confirmPasswordInput.typeText(password);
    }

}
