package com.digitaslbi.selenium;

import com.digitaslbi.selenium.webdriver.RemoteWebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface PostCreationAction {

    void execute(RemoteWebDriver driver);

    public static class DepartingPostAction implements PostCreationAction {

        @Override
        public void execute(RemoteWebDriver driver) {
            RemoteWebDriverFactory.instance().findElement(By.id("departing"))
                    .click();
        }
    }

    public static class ArrivingPostAction implements PostCreationAction {

        @Override
        public void execute(RemoteWebDriver driver) {
            RemoteWebDriverFactory.instance().findElement(By.id("arriving"))
                    .click();

        }
    }
}
