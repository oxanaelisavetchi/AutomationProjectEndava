package com.automation.project.actions;

import com.automation.project.configuration.ConfigurationProperties;
import com.automation.project.context.ScenarioContext;
import com.automation.project.drivers.DriverFactory;
import com.automation.project.pages.BasePage;
import com.automation.project.utilities.PageManager;


public class NavigationActions extends DriverFactory {

    // static???
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private final String baseUrl = ConfigurationProperties.getConfigPropertyValue("base.url");

    // move this to
    public void navigateToPage(String pageName) {
        BasePage page = PageManager.getPageByName(pageName);
        String fullUrl = baseUrl + page.getURL();
        navigateToLink(fullUrl);
        scenarioContext.setCurrentPage(page);
    }

    private void navigateToLink(String link) {

        DriverFactory.getDriver().navigate().to(link);
    }
}
