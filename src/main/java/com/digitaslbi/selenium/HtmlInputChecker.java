package com.digitaslbi.selenium;

import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class HtmlInputChecker {

    public void validateNameAttribute(List<WebElement> inputs) {
        for (WebElement input : inputs) {
            assertThat("name attribute missing of form input ",
                    input.getAttribute("name"), not(nullValue()));
        }
    }

}
