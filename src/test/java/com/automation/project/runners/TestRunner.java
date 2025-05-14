package com.automation.project.runners;


import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.FILTER_TAGS_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@SelectPackages("com.automation.project")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "pretty, html:target/evidence/cucumber-report/report.html")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@ProductsFunctionality")

public class TestRunner {
}
