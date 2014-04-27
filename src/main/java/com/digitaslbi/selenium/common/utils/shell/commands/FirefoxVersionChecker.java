package com.digitaslbi.selenium.common.utils.shell.commands;

public class FirefoxVersionChecker extends BaseShellCommand {
    @Override
    public String[] getText() {
        return new String[]{
                "/bin/sh",
                "-c",
                "firefox -version"
        };
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

}
