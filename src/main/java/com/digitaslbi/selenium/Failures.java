package com.digitaslbi.selenium;

import com.digitaslbi.selenium.webdriver.RemoteWebDriverFactory;
import org.apache.commons.lang3.BooleanUtils;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.fail;

public class Failures {
    public static final String AT = " at ";
    public static final String COULD_NOT_FIND = "Could not find ";
    public static final String FOUND = "Found ";
    public static final String TIMED_OUT_WAITING_FOR = "Timed out waiting for ";
    private static final RemoteWebDriver webDriver = RemoteWebDriverFactory.instance();

    public static void failOnFound(String description) {

        failAs(FOUND, description);
    }

    public static void failOnNotFound(String description) {
        failAs(COULD_NOT_FIND, description);
    }

    public static void failOnTimeout(String description) {
        failAs(TIMED_OUT_WAITING_FOR, description);
    }

    private static void failAs(String cause, String description) {
        failIfCausedByKnownError();
        failWithFormattedMessage(cause, description);
    }

    private static void failWithFormattedMessage(String cause, String description) {
        AssertWrapper.fail(formatFailureMessage(cause, description));
    }

    public static String formatDescriptionOnFail(String description) {
        String failureMessage = String.format("%s%s%s%s", "Incorrect value for ", description, AT, webDriver.getCurrentUrl());
        if (shouldLogPageSource()) {
            failureMessage = addSourceToErrorMessage(failureMessage);
        }
        return failureMessage;
    }

    private static String formatFailureMessage(String cause, String description) {
        String failureMessage = String.format("%s%s%s%s", cause, description, AT, webDriver.getCurrentUrl());
        if (shouldLogPageSource()) {
            failureMessage = addSourceToErrorMessage(failureMessage);
        }
        return failureMessage;
    }

    private static String addSourceToErrorMessage(String description) {
        String WHITESPACE = "\n\n\n\n\n";
        description = String.format("%s%s%s", description, WHITESPACE, webDriver.getPageSource());
        return description;
    }

    private static boolean shouldLogPageSource() {
        return BooleanUtils.toBoolean(System.getProperty("showSource"));
    }

    private static void failIfCausedByKnownError() {
        compareTitleToKnownErrorCases(getPageTitle());
    }

    private static void compareTitleToKnownErrorCases(String pageTitle) {
        for (WebsiteConnectionProblem problem : WebsiteConnectionProblem.values()) {
            failOnMatch(pageTitle, problem);
        }
    }

    private static void failOnMatch(String pageTitle, WebsiteConnectionProblem problem) {
        String problemTitle = problem.getBrowserPageTitle();
        if (pageTitle.equalsIgnoreCase(problemTitle)) {
            fail(problem.getFriendlyMessage());
        }
    }

    private static String getPageTitle() {
        return webDriver.getTitle() != null
                    ? webDriver.getTitle()
                    : "";
    }
}
