package orangeHRM.pages.components;

import orangeHRM.base.BaseElement;
import orangeHRM.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends BasePage {

    protected BaseElement inputField;
    protected BaseElement loginLink;

    public Header(WebDriver driver) {
        super(driver);
        String baseXpath = "//header";
        inputField = new BaseElement(driver, "Input field on header", By.xpath(baseXpath + "//input[@type='search']"));
        loginLink = new BaseElement(driver, "Login link on header", By.className("login-link"));
    }

    @Step("Click on login link inside header")
    public LoginModal clickOnLogin() {
        loginLink.click();
        return new LoginModal(driver);
    }
}
