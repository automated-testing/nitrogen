package com.digitaslbi.selenium.html;

import com.digitaslbi.selenium.common.controls.BaseElement;
import com.digitaslbi.selenium.common.utils.Description;
import com.digitaslbi.selenium.common.utils.LocatedBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Description("link")
@LocatedBy(tagName = "a")
public class Link extends BaseElement {

    public Link(WebElement webElement, String description) {
        super(webElement, description);
    }

}
