package orangeHRM.pages;

import orangeHRM.base.BaseElement;
import orangeHRM.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideBar extends BasePage {
    protected BaseElement adminTab;

    public SideBar(WebDriver driver) {
        super(driver, "Sidebar");
        String navigationItem = "//a[contains(@href,\"%s\")]";
        adminTab = new BaseElement(driver, "Admin tab on " + this.getName(), By.xpath(navigationItem.formatted("admin")));
    }

    public AdminPage clickOnAdminTab() {
        adminTab.click();
        return new AdminPage(driver);
    }
}
