package com.automation.project.steps;

import com.automation.project.actions.NavigationActions;
import com.automation.project.asserts.CustomAssert;
import com.automation.project.context.ScenarioContext;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.Matchers.is;

@Slf4j
public class CommonSteps {

    private final NavigationActions navigationActions = new NavigationActions();

    @Given("user navigates to {string} page")
    public void userNavigatesTo(String pageName) {
        log.info("Navigating to page: {}", pageName);

        navigationActions.navigateToPage(pageName);
        boolean isDisplayed = ScenarioContext.getCurrentPage().getAnchorElement().isDisplayed();

        CustomAssert.assertThat(
                String.format("Verify user is on '%s' page", pageName),
                isDisplayed,
                is(true)
        );
    }
}
