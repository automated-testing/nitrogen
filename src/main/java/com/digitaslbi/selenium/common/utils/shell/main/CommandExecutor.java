package com.digitaslbi.selenium.common.utils.shell.main;

import com.digitaslbi.selenium.common.utils.ShellCommand;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;

public class CommandExecutor {
    public void exec(ShellCommand shellCommand) {
        logOutputOf(shellCommand);
    }

    private void logOutputOf(ShellCommand command) {
        try {
            if (command.isValid()) {
                Runtime runTime = Runtime.getRuntime();
                Process process = runTime.exec(command.getText());
                InputStream inputStream = process.getInputStream();

                int marker;
                char currentChar;
                StringBuilder output = new StringBuilder();

                marker = inputStream.read();
                while (marker != -1) {
                    currentChar = (char) marker;
                    output.append(String.valueOf(currentChar));
                    marker = inputStream.read();
                }
                System.out.println("\n\n***************  ENVIRONMENT LOG FOR: " + command.getName() + "  ***************");
                String result = output.toString().trim();
                if (StringUtils.isEmpty(result)) {
                    result = "No output returned";
                }
                System.out.println(result);
                System.out.println("***************  END LOG  ***************\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
