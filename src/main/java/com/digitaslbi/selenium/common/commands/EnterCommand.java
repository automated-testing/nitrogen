package com.digitaslbi.selenium.common.commands;

import com.digitaslbi.selenium.common.selectables.KeyboardSelectable;
import com.digitaslbi.selenium.webdriver.RemoteWebDriverFactory;
import org.openqa.selenium.WebElement;

public class EnterCommand <T extends KeyboardSelectable> implements Command<T> {

    @Override
    public void perform(T selectable) {
        WebElement inputBoxElement = RemoteWebDriverFactory.instance().findElement(selectable.getKeyboardLocator());
        RemoteWebDriverFactory.instance().executeScript(createPressEnterEvent() + triggerPressEnterEvent(inputBoxElement));
    }

    private String triggerPressEnterEvent(WebElement element) {
        return "$('#" + element.getAttribute("id")  + "').trigger('focus').trigger('keypress').keydown().trigger(e)";
    }

    private String createPressEnterEvent() {
        return "var e = jQuery.Event('keydown');e.which = 13;e.keyCode = 13;";
    }
}
