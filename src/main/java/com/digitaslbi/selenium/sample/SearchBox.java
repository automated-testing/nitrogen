package com.digitaslbi.selenium.sample;

import com.digitaslbi.selenium.common.controls.BaseElement;
import com.digitaslbi.selenium.common.utils.Description;
import com.digitaslbi.selenium.common.utils.LocatedBy;
import org.openqa.selenium.WebElement;

@Description("Search Box")
@LocatedBy(name = "q")
public class SearchBox extends BaseElement {

    public SearchBox(final WebElement webElement, final String description) {
        super(webElement, description);
    }

}
