package stepDefinitions.windows;

import com.iportman.automation.allModules.login.windows.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class LoginWindowStepDefs {

    @Given("the {string} user {string} is logged into iportman application")
    public void theUserIsLoggedIntoIportmanApplication(String posUser, String userId) {
        LoginPage loginPage= new LoginPage();
        loginPage.loginToAPP(posUser,userId);
    }

    @When("User sign up with following details")
    public void userSignUpWithFollowingDetails(DataTable data) {
      List<Map<String,String>>userMap =  data.asMaps(String.class,String.class);
        System.out.println(userMap.get(0));

    }
}
