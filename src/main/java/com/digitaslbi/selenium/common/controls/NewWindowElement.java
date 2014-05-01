package com.digitaslbi.selenium.common.controls;

import com.digitaslbi.selenium.webdriver.RemoteWebDriverFactory;
import org.openqa.selenium.WebElement;

public class NewWindowElement extends BaseElement{

    static{
        RemoteWebDriverFactory.switchToNewWindow();
    }

    public NewWindowElement(WebElement webElement, String description) {
        super(webElement, description);
    }
}
