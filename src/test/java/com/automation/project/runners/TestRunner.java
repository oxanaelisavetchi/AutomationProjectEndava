package com.automation.project.runners;


import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@SelectPackages("com.automation.project")
//@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@RestApiFunctionality")

public class TestRunner {
}
