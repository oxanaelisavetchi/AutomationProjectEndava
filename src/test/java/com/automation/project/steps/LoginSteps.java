package com.automation.project.steps;

import com.automation.project.actions.LoginActions;
import com.automation.project.asserts.CustomAssert;
import com.automation.project.context.ScenarioContext;
import com.automation.project.enums.ErrorMessages;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.Matchers.is;

@Slf4j
public class LoginSteps {

    private final LoginActions logInActions = new LoginActions();
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @When("user enters the {string} and {string}")
    public void userLogsWithCredentials(String userName, String password) throws Throwable {
        log.info("Trying to log in with username: '{}' and password: '{}'", userName, password);
        logInActions.logIn(userName, password);
        // add log
    }

    @Then("user enters on product page")
    public void userEntersOnProductPage() {
        CustomAssert.assertThat("User in on Account page  ", scenarioContext.getCurrentPage().getAnchorElement().isDisplayed(), is(true));
        // add log
    }

    @Then("user receives message {string}")
    public void userReceivesMessage(String messageKey) {
        String actualError = logInActions.checkError();
        String expectedError = ErrorMessages.valueOf(messageKey).getMessage();
        CustomAssert.assertThat("Error message validation", actualError, is(expectedError));
    }
}
