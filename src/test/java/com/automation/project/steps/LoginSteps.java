package com.automation.project.steps;

import com.automation.project.actions.LoginActions;
import com.automation.project.asserts.CustomAssert;
import com.automation.project.context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.Matchers.is;

public class LoginSteps {

    private final LoginActions logInActions = new LoginActions();
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @When("user enters the {string} and {string}")
    public void userLogsWithCredentials(String userName, String password) throws Throwable {
        logInActions.logIn(userName, password);
    }

    @Then("products label is displayed")
    public void productsLabelIsDisplayed() {
        CustomAssert.assertThat("User in on Account page  ", scenarioContext.getCurrentPage().getAnchorElement().isDisplayed(), is(true));
        logInActions.logOut();
    }

    @Then("error message is displayed {string}")
    public void errorLabelIsDisplayed(String message) {
        CustomAssert.assertThat(message, message.equals(logInActions.checkError()), is(true));
    }
}
