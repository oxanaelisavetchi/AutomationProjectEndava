package com.automation.project.drivers;

import com.automation.project.configuration.ConfigurationProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;


public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {

            String browser = System.getProperty("browser", ConfigurationProperties.getConfigPropertyValue("browser"));

            switch (browser.toLowerCase()) {
                case "firefox" -> driver = new FirefoxDriver();
                case "ie" -> driver = new InternetExplorerDriver();
                case "edge" -> driver = new EdgeDriver();
                default -> driver = new ChromeDriver();
            }

            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    public static void waitFor(int sec) {
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

}

