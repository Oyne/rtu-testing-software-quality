package orangeHRM.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LeaveTableRow {
    private final WebElement rowRoot;
    private final By cellLocator = By.className("oxd-table-cell");
    private final By kebabMenuLocator = By.cssSelector(".bi-three-dots-vertical");

    public LeaveTableRow(WebElement rowRoot) {
        this.rowRoot = rowRoot;
    }

    public String getDate() {
        return getCellText(1);
    }

    public String getEmployeeName() {
        return getCellText(2);
    }

    public String getLeaveType() {
        return getCellText(3);
    }

    public String getLeaveBalance() {
        return getCellText(4);
    }

    public String getNumberOfDays() {
        return getCellText(5);
    }

    public String getStatus() {
        return getCellText(6);
    }

    public String getComments() {
        return getCellText(7);
    }

    private String getCellText(int index) {
        return rowRoot.findElements(cellLocator).get(index).getText().trim();
    }

    public void clickKebabMenu() {
        rowRoot.findElement(kebabMenuLocator).click();
    }
}
