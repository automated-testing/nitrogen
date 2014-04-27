package com.digitaslbi.selenium.common.utils;

import com.digitaslbi.selenium.webdriver.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * TODO
 * This class is a temporary wrapper over the Wait class to simplify the unit
 * testing of the ElementsBuilder class. This should be removed as soon as we
 * refactor the Wait class to remove all the static methods and replace them
 * with instance methods instead.
 */
public class WaitWrapper {
    public void untilElementIsDisplayed(By locator, String description) {
        Wait.untilElementIsDisplayed(locator, description);
    }

    public void untilControlIsVisible(WebElement element, String childDescription) {
        Wait.untilControlIsVisible(element, childDescription);
    }

    public void untilElementIsPresent(By locator, String description) {
        Wait.untilElementIsPresent(locator, description);
    }
}
