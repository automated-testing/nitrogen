package com.digitaslbi.selenium.html;

import com.digitaslbi.selenium.common.controls.BaseElement;
import com.digitaslbi.selenium.common.utils.Description;
import com.digitaslbi.selenium.common.utils.LocatedBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Description("Level 2 heading (h2)")
@LocatedBy(tagName = "h2")
public class LevelTwoHeading extends BaseElement {

    public LevelTwoHeading(WebElement webElement, String description) {
        super(webElement, description);
    }

}
