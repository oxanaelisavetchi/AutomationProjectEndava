package com.automation.project.configuration;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigurationProperties {

    private static final ResourceBundle configProperties = ResourceBundle.getBundle("config/config");
    private static final ResourceBundle pathProperties = ResourceBundle.getBundle("config/paths");

    public static String getConfigPropertyValue(String key) {
        try {
            return configProperties.getString(key);
        } catch (MissingResourceException e) {
            System.err.println(" Missing config key: " + key);
            return ""; // fallback value
        }
    }

    public static String getPathPropertyValue(String key) {
        try {
            return pathProperties.getString(key);
        } catch (MissingResourceException e) {
            System.err.println(" Missing path key: " + key);
            return ""; // fallback value
        }
    }
}
