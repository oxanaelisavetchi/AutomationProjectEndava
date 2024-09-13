package com.automation.project.hooks;

import com.automation.project.configuration.ConfigurationProperties;
import com.automation.project.context.ScenarioContext;
import com.automation.project.drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @AfterStep
    public void takeScreenshot(Scenario scenario) throws Exception {

        if (!scenario.getName().contains("rest api functionality")) {
            File scr = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            File dest = new File(String.format("%s %s %s.png",
                    ConfigurationProperties.getPathPropertyValue("path.screenshots.folder"),
                    DateTimeFormatter.ofPattern("uuuu-MM-dd HH-mm-ss").format(LocalDateTime.now()),
                    scenario.getName()));
            FileUtils.copyFile(scr, dest);
        }
    }

    @After
    public void closeSession() {
        DriverFactory.quitDriver();
        scenarioContext.setCurrentPage(null);
        ScenarioContext.closeScenario();
    }

}