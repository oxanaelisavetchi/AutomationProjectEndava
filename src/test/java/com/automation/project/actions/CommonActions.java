package com.automation.project.actions;


import com.automation.project.context.ScenarioContext;
import com.automation.project.drivers.DriverFactory;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Method;

public class CommonActions extends DriverFactory {

    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();


    public void clickButton(String name) throws Throwable {
        Method method = scenarioContext.getCurrentPage().getClass().getMethod("get" + name + "Button");
        ((WebElement) method.invoke(scenarioContext.getCurrentPage())).click();
    }
}