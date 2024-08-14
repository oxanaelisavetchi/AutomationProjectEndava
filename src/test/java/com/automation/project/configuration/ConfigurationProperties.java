package com.automation.project.configuration;


import java.util.ResourceBundle;

public class ConfigurationProperties {

    private static final ResourceBundle configProperties = ResourceBundle.getBundle("config/config");
    private static final ResourceBundle pathProperties = ResourceBundle.getBundle("config/paths");


    public static String getConfigPropertyValue(String key) {
        return configProperties.getString(key);
    }

    public static String getPathPropertyValue(String key) {
        return pathProperties.getString(key);
    }

}