package com.digitaslbi.selenium.html;

import com.digitaslbi.selenium.common.controls.BaseElement;
import com.digitaslbi.selenium.common.utils.Description;
import com.digitaslbi.selenium.common.utils.LocatedBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Description("form")
@LocatedBy(tagName = "form")
public class Form extends BaseElement {

    public Form(WebElement webElement, String description) {
        super(webElement, description);
    }

}
