package com.digitaslbi.selenium.common.commands;

import com.digitaslbi.selenium.common.selectables.KeyboardSelectable;
import com.digitaslbi.selenium.webdriver.RemoteWebDriverFactory;
import org.openqa.selenium.Keys;

public class TabCommand <T extends KeyboardSelectable> implements Command<T> {
    @Override
    public void perform(T selectable) {
        RemoteWebDriverFactory.instance().findElement(selectable.getKeyboardLocator()).sendKeys(Keys.TAB);
    }
}
