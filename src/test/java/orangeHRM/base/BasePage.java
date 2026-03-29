package orangeHRM.base;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;
    protected String name;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage(WebDriver driver, String name) {
        this.driver = driver;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
