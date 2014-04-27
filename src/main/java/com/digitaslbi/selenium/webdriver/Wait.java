package com.digitaslbi.selenium.webdriver;

import com.google.common.base.Predicate;
import com.digitaslbi.selenium.common.controls.BaseElement;
import com.digitaslbi.selenium.common.predicates.AllAjaxResponsesReceived;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.digitaslbi.selenium.Failures.failOnTimeout;
import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Wait {

    public static void forAllAJAXRequestsToFinish() {
        waitUntil(new WaitCondition() {
            @Override
            public void apply() {
                RemoteWebDriverFactory.waiting().until(new AllAjaxResponsesReceived());
            }

            @Override
            public String getErrorMessage() {
                return "AJAX responses to return";
            }
        });
    }

    private static void waitUntil(WaitCondition waitCondition) {
        try {
            waitCondition.apply();
        } catch (TimeoutException e) {
            failOnTimeout(waitCondition.getErrorMessage());
        }
    }

    public static void untilElementIsClickable(final By by){
        waitUntil(new WaitCondition() {
            int matchingElementCount;

            @Override
            public void apply() {
                matchingElementCount = RemoteWebDriverFactory.instance().findElements(by).size();
                RemoteWebDriverFactory.waiting().until(elementToBeClickable(by));
            }

            @Override
            public String getErrorMessage() {
                return String.format("%s to be clickable. %d elements matching the By found in the DOM", by, matchingElementCount);
            }
        });
    }

    /**
     * TODO:
     * Controls ONLY — Move to separate class.
     */
    public static void untilControlIsVisible(final WebElement element, final String description) {
        waitUntil(new WaitCondition() {
            @Override
            public void apply() {

                RemoteWebDriverFactory.waiting().until(visibilityOf(element));
            }

            @Override
            public String getErrorMessage() {
                return String.format("%s to be visible.", description);
            }
        });
    }

    public static void untilElementIsDisplayed(final By by, final String description) {
        waitUntil(new WaitCondition() {
            @Override
            public void apply() {

                RemoteWebDriverFactory.waiting().until(visibilityOfElementLocated(by));
            }

            @Override
            public String getErrorMessage() {
                return String.format("%s to be visible.", description);
            }
        });
    }

    public static void untilElementIsPresent(final By by, final String description) {
        waitUntil(new WaitCondition() {
            @Override
            public void apply() {

                RemoteWebDriverFactory.waiting().until(presenceOfElementLocated(by));
            }

            @Override
            public String getErrorMessage() {
                return String.format("%s to be present.", description);
            }
        });
    }

    public static <T extends BaseElement>void untilElementIsDisplayed(final Class<T> clazz) {
        final By elementLocator = getElementLocator(clazz);
        waitUntil(new WaitCondition() {
            @Override
            public void apply() {
                RemoteWebDriverFactory.waiting().until(visibilityOfElementLocated(elementLocator));
            }

            @Override
            public String getErrorMessage() {
                return String.format("%sto be displayed", elementLocator);
            }
        });
    }

    public static <T extends BaseElement>void untilElementIsHidden(final Class<T> clazz) {
        final By elementLocator = getElementLocator(clazz);
        waitUntil(new WaitCondition() {
            @Override
            public void apply() {
                RemoteWebDriverFactory.waiting().until(invisibilityOfElementLocated(elementLocator));
            }

            @Override
            public String getErrorMessage() {
                return String.format("%sto be hidden", elementLocator);
            }
        });
    }


    private static <T extends BaseElement> By getElementLocator(Class<T> clazz) {
        try {
            return (By) clazz.getField(BaseElement.LOCATOR_FIELD).get(By.class);
        } catch (Exception e) {
            fail(String.format("Element %s does not expose a LOCATOR field", clazz.getSimpleName()));
        }
        return null;
    }

    public static void untilPageUrlHasChanged(final String sourceUrl) {
        waitUntil(new WaitCondition() {
            @Override
            public void apply() {
                RemoteWebDriverFactory.waiting().until(new Predicate<WebDriver>(){
                    @Override
                    public boolean apply(WebDriver webDriver) {
                        return !webDriver.getCurrentUrl().equals(sourceUrl);
                    }
                });
            }

            @Override
            public String getErrorMessage() {
                return String.format("driver to leave page %s", sourceUrl);
            }
        });
    }


}

abstract class WaitCondition{
    public abstract void apply();

    public abstract String getErrorMessage();
}
