package com.digitaslbi.selenium.common.commands;

import com.digitaslbi.selenium.common.selectables.Selectable;
import com.digitaslbi.selenium.webdriver.RemoteWebDriverFactory;
import org.openqa.selenium.By;

public class FindAndClickCommand<T extends Selectable> implements Command<T> {
    @Override
    public void perform(T selectable) {
        click(selectable.getSelector());
    }

    protected void click(By locator) {
        RemoteWebDriverFactory.instance().findElement(locator).click();
    }


}