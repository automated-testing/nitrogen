package com.digitaslbi.selenium.html;

import com.digitaslbi.selenium.common.controls.BaseElement;
import com.digitaslbi.selenium.common.utils.Description;
import com.digitaslbi.selenium.common.utils.LocatedBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Description("table")
@LocatedBy(tagName = "table")
public class Table extends BaseElement {

    public Table(WebElement webElement, String description) {
        super(webElement, description);
    }

}
