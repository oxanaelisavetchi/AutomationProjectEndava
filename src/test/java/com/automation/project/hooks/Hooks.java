package com.automation.project.hooks;

import com.automation.project.context.ScenarioContext;
import com.automation.project.drivers.DriverFactory;
import com.automation.project.configuration.ConfigurationProperties;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.guieffect.qual.UI;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {
    private int screenNr = 1;

    private ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @Before

    public void scenarioSetup() {
        DriverFactory.getDriver();
    }

    @AfterStep
    public void getScreenshot(Scenario scenario) throws Exception {

        File scr = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

        new File(ConfigurationProperties.getPathPropertyValue("path.screenshots.folder")).mkdir();

        File dest = new File(ConfigurationProperties.getPathPropertyValue("path.screenshots.folder") + scenario.getName() + "_screenshot_"
                + screenNr + "_" + timestamp() + ".png");

        FileUtils.copyFile(scr, dest);

        screenNr++;
    }

    private String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }


    @After
    public void closeSession() {
        DriverFactory.quitDriver();
        scenarioContext.setCurrentPage(null);
        ScenarioContext.closeScenario();
    }

}