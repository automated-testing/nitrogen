package com.digitaslbi.selenium.common.utils;

import com.digitaslbi.selenium.webdriver.RemoteWebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementsFinder {
    public WebElement findComponentElement(By locator) {
        return RemoteWebDriverFactory.instance().findElement(locator);
    }

    public WebElement findChildElement(By locator, WebElement parentElement) {

        return parentElement.findElement(locator);
    }

    public List<WebElement> findChildElements(By locator, WebElement parentElement) {

        return parentElement.findElements(locator);
    }

    public boolean doesElementExist(By locator) {
        return RemoteWebDriverFactory.instance().findElements(locator).size() > 0;
    }

    public String getCurrentUrl() {
        return RemoteWebDriverFactory.instance().getCurrentUrl();
    }
}
