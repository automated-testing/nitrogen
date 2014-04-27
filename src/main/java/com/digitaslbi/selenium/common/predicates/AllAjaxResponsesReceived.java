package com.digitaslbi.selenium.common.predicates;

import com.google.common.base.Predicate;
import com.digitaslbi.selenium.webdriver.RemoteWebDriverFactory;
import org.openqa.selenium.WebDriver;

public class AllAjaxResponsesReceived implements Predicate<WebDriver> {

    @Override
    public boolean apply(WebDriver webDriver) {
        return (Boolean) RemoteWebDriverFactory.instance().executeScript("return jQuery.active == 0");
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
