package com.digitaslbi.selenium.common.utils.shell.commands;

public class ReportAgentUptime extends BaseShellCommand {
    @Override
    public String[] getText() {
        return new String[] {
                "/bin/sh",
                "-c",
                "uptime"
        };
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
