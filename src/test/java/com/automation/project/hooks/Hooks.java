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

   /* @Before(order = 1)
    public void beforeAnyScenario() {
        System.out.println(">>> Rulez before pentru toate scenariile (ORDER = 1)");
    }

    @Before("@UI")
    public void beforeUIOnly() {
        System.out.println(">>> Pregătesc WebDriver pentru test UI (@UI)");
        DriverFactory.getDriver();
    }*/

    @AfterStep
    // TODO: discuss -> improve
    public void takeScreenshot(Scenario scenario) throws Exception {

        // variable REST_API_MESSAGE
        if (!scenario.getName().contains("rest api functionality")) {
            File scr = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            File dest = new File(String.format("%s %s %s.png",
                    // move to variable
                    ConfigurationProperties.getPathPropertyValue("path.screenshots.folder"),
                    // the same for "uuuu-MM-dd HH-mm-ss"
                    DateTimeFormatter.ofPattern("uuuu-MM-dd HH-mm-ss").format(LocalDateTime.now()),
                    scenario.getName()));
            FileUtils.copyFile(scr, dest);
        }
    }
    /*@After("@Api")
    public void afterApiScenario() {
        System.out.println(">>> Cleanup API după scenariu cu tag @Api");
        // aici poți adăuga logică specială dacă e nevoie
    }*/

    // maybe add clear for scenario after each step?
    @After
    public void closeSession() {
        DriverFactory.quitDriver();
        ScenarioContext.setCurrentPage(null);
        ScenarioContext.closeScenario();
    }

    // @AfterEach clear ScenarioContext

}