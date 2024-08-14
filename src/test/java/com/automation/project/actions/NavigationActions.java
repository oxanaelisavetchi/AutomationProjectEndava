package com.automation.project.actions;

import com.automation.project.context.ScenarioContext;
import com.automation.project.drivers.DriverFactory;
import com.automation.project.pages.BasePage;
import com.automation.project.configuration.ConfigurationProperties;
import com.automation.project.utilities.PageManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class NavigationActions extends DriverFactory {

    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    public void navigateToPage(String pageName) {
        BasePage page = PageManager.getPageByName(pageName);
        navigateToLink(ConfigurationProperties.getConfigPropertyValue("base.url") + page.getURL());
        scenarioContext.setCurrentPage(page);
    }

    private void navigateToLink(String link) {
        DriverFactory.getDriver().navigate().to(link);
    }

    public WebElement moveToElement(WebElement webElement) {Actions actions = new Actions(getDriver());

        if (ConfigurationProperties.getConfigPropertyValue("browser").equals("firefox")) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(false);", webElement);
        }
        actions.moveToElement(webElement).perform();

        return webElement;
    }
}
