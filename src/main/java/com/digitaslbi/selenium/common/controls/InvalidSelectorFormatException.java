package com.digitaslbi.selenium.common.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

public class InvalidSelectorFormatException extends RuntimeException {
    public <T extends BaseElement> InvalidSelectorFormatException(Class<T> clazz, By by, WebDriverException e) {
        super("Could not create an element using the LOCATOR within " + clazz.getSimpleName() +
              "\nThe subtype of the original 'By' used (e.g. ID/CSS selector/XPATH/tag name) is not available via reflection," +
              "\nbut please recheck that the declared subtype aligns with the value used below:\n\n\t" + by.toString() + "\n\n", e);
    }
}
