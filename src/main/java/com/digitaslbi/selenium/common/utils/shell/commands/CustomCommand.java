package com.digitaslbi.selenium.common.utils.shell.commands;

public class CustomCommand extends BaseShellCommand {
    @Override
    public String[] getText() {
        String[] commands = {
                "/bin/sh",
                "-c",
                getCommand()
        };
        return commands;
    }

    private String getCommand() {
        return System.getenv("COMMAND");
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean isValid() {
        return getCommand() != null && getCommand().length() > 0;
    }
}
