package com.automation.project.utilities;

import com.automation.project.configuration.ConfigurationProperties;
import com.automation.project.drivers.DriverFactory;
import com.automation.project.pages.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Slf4j
public class PageManager {
    private static final String PATH_TO_PAGES_PACKAGE = ConfigurationProperties.getPathPropertyValue("path.pages");

    public static BasePage getPageByName(String pageName) {
        BasePage basePage = null;
        try {
            Class<?> cl = Class.forName(PATH_TO_PAGES_PACKAGE + pageName + "Page");
            Constructor<?> constructor = cl.getConstructor(WebDriver.class);
            basePage = (BasePage) constructor.newInstance(new Object[]{DriverFactory.getDriver()});
        } catch (ClassNotFoundException | InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.error(e.getMessage());
        }
        return basePage;
    }
}

