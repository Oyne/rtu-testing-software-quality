package orangeHRM.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserTableRow {
    private final WebElement rowRoot;
    private final By cellLocator = By.className("oxd-table-cell");
    private final By deleteButtonLocator = By.cssSelector(".bi-trash");
    private final By editButtonLocator = By.cssSelector(".bi-pencil-fill");
    private final By checkboxLocator = By.cssSelector("input[type='checkbox']");

    public UserTableRow(WebElement rowRoot) {
        this.rowRoot = rowRoot;
    }

    public String getUsername() {
        return getCellText(1);
    }

    public String getUserRole() {
        return getCellText(2);
    }

    public String getEmployeeName() {
        return getCellText(3);
    }

    public String getStatus() {
        return getCellText(4);
    }

    private String getCellText(int index) {
        return rowRoot.findElements(cellLocator).get(index).getText().trim();
    }

    public void clickDelete() {
        rowRoot.findElement(deleteButtonLocator).click();
    }

    public void clickEdit() {
        rowRoot.findElement(editButtonLocator).click();
    }

    public void toggleCheckbox() {
        rowRoot.findElement(checkboxLocator).click();
    }
}