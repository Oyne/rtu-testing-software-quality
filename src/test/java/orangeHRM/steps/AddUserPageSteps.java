package orangeHRM.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import orangeHRM.hooks.Hooks;
import orangeHRM.pages.AddUserPage;
import orangeHRM.pages.AdminPage;
import orangeHRM.pages.LoginPage;
import orangeHRM.pages.SideBar;

public class AddUserPageSteps {
    private AddUserPage addUserPage = new AddUserPage(Hooks.driver);

    @Given("the user clicks cancel")
    public void cancel() {
       addUserPage.clickCancel();
    }

}
