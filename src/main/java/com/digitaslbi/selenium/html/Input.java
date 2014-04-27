package com.digitaslbi.selenium.html;

import com.digitaslbi.selenium.common.controls.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class Input extends BaseElement {
    public Input(By by, String description, BaseElement parent) {
        super(by, description, parent);
    }

    public Input(WebElement webElement, String description) {
        super(webElement, description);
    }

    public abstract void fill();
}
