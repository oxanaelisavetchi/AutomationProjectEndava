package com.automation.project.steps;

import com.automation.project.actions.NavigationActions;
import com.automation.project.asserts.CustomAssert;
import com.automation.project.context.ScenarioContext;
import io.cucumber.java.en.Given;

import static org.hamcrest.Matchers.is;

public class CommonSteps {

    private final NavigationActions navigationActions = new NavigationActions();
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @Given("user navigates to '(.*)' page$")
    public void userNavigatesTo(String pageName) {
        navigationActions.navigateToPage(pageName);
        CustomAssert.assertThat("User is on " + pageName + " page", scenarioContext.getCurrentPage().getAnchorElement().isDisplayed(), is(true));
    }
}