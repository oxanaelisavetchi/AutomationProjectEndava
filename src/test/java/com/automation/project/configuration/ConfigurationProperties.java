package com.automation.project.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

public class ConfigurationProperties {

    private static final ResourceBundle configProperties = ResourceBundle.getBundle("config/config");
    private static final ResourceBundle pathProperties = ResourceBundle.getBundle("config/paths");
// that will happen if property file is empty?

//    static {
//        try (InputStream input = PropertiesManager.class.getClassLoader().getResourceAsStream("properties/config.properties")) {
//            if (input != null) {
//                PROPERTIES.load(input);
//            } else {
//                LogManager.getLogger().error("Unable to find properties file");
//            }
//        } catch (IOException e) {
//            LogManager.getLogger().error("Error loading properties file", e);
//        }
//    }

    public static String getConfigPropertyValue(String key) {
        return configProperties.getString(key);
    }

    public static String getPathPropertyValue(String key) {
        return pathProperties.getString(key);
    }

}