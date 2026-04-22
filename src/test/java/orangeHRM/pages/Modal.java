package orangeHRM.pages;

import orangeHRM.base.BaseElement;
import orangeHRM.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Modal extends BasePage {

    public Modal(WebDriver driver) {
        super(driver, "Modal");
    }

    public void clickOnButtonByName(String name) {
        BaseElement button = new BaseElement(driver, "%s button".formatted(name), By.xpath("//button[normalize-space()='%s']".formatted(name)));
        button.click();
    }
}
