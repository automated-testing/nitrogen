package com.digitaslbi.selenium.common.commands;

import com.digitaslbi.selenium.common.selectables.Selectable;

public interface Command<T extends Selectable> {
    void perform(T selectable);
}
