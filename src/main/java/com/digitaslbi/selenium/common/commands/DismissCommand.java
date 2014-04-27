package com.digitaslbi.selenium.common.commands;

import com.digitaslbi.selenium.common.selectables.Dismissible;

public class DismissCommand <T extends Dismissible>extends FindAndClickCommand<T> {
    @Override
    public void perform(T selectable) {
        click(selectable.getDismissBy());
    }
}
