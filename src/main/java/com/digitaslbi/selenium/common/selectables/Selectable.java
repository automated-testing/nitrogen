package com.digitaslbi.selenium.common.selectables;

import com.digitaslbi.selenium.common.commands.Command;
import org.openqa.selenium.By;

public interface Selectable {
    By getSelector();
    void execute(Command<?> command);
}
