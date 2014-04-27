package com.digitaslbi.selenium.html;

import com.digitaslbi.selenium.common.controls.BaseElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.fail;

public class SelectList extends Select {
    private BaseElement baseElement;

    public SelectList(BaseElement baseElement) {
        super(baseElement.getWebElement());
        this.baseElement = baseElement;
    }

    @Override
    public void selectByVisibleText(String text) {
        try {
            super.selectByVisibleText(text);
        } catch (NoSuchElementException e) {
            String failureMessage = "Cannot set value of %s to '%s'. Cannot find an option with that value in the list.";
            fail(String.format(failureMessage, baseElement.getDescription(), text));
        }
    }
}
