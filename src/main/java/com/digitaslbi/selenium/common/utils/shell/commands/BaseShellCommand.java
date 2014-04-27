package com.digitaslbi.selenium.common.utils.shell.commands;

import com.digitaslbi.selenium.common.utils.ShellCommand;
import org.apache.commons.lang3.ArrayUtils;

public abstract class BaseShellCommand implements ShellCommand{
    @Override
    public boolean isValid() {
        return ArrayUtils.isNotEmpty(getText());
    }
}
