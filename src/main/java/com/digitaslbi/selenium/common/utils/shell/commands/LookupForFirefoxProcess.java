package com.digitaslbi.selenium.common.utils.shell.commands;

public class LookupForFirefoxProcess extends BaseShellCommand {
    @Override
    public String[] getText() {
        return new String[]{
                "/bin/sh",
                "-c",
                "ps -ef | grep firefox"
        };
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
