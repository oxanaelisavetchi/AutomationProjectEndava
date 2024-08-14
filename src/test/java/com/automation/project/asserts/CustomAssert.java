package com.automation.project.asserts;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

@Slf4j
public class CustomAssert {

    public static <T> void assertThat(String message, T actual, Matcher<T> matcher) {
        String fullMessage = "Assert that " + message;
        String logMessage = getActualExpectedMessage(fullMessage, String.valueOf(actual), String.valueOf(matcher));
        try {
            MatcherAssert.assertThat(fullMessage, actual, matcher);
            log.info(logMessage);
        } catch (AssertionError error) {
            log.info(logMessage);
            throw new AssertionError(error.getMessage());
        }
    }

    private static String getActualExpectedMessage(String fullMessage, String actual, String expected) {
        return String.format("%s\n - [ ACTUAL ]: %s\n - [ EXPECTED ]: %s", fullMessage, actual, expected);
    }

}
