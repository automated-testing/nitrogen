package com.digitaslbi.selenium;

import com.digitaslbi.selenium.common.utils.shell.commands.CustomCommand;
import com.digitaslbi.selenium.common.utils.shell.commands.FirefoxVersionChecker;
import com.digitaslbi.selenium.common.utils.shell.commands.LookupForFirefoxProcess;
import com.digitaslbi.selenium.common.utils.shell.commands.ReportAgentUptime;
import com.digitaslbi.selenium.common.utils.shell.main.CommandExecutor;
import com.digitaslbi.selenium.webdriver.ScreenShooter;
import org.apache.commons.lang3.StringUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;

public class TakeScreenShotsOnFailureWatcher extends TestWatcher {

    public static final String HYPHEN = "-";
    public static final String PNG_SUFFIX = ".png";
    public static final String SCREENSHOTS_DIR = System.getProperty("screenshot.failure.dir");
    public static final int INDEX_OF_TOP_LEVEL_PACKAGE = 4;

    @Override
    protected void starting(Description description) {
        new CommandExecutor().exec(new LookupForFirefoxProcess());
        new CommandExecutor().exec(new ReportAgentUptime());
        new CommandExecutor().exec(new FirefoxVersionChecker());
        new CommandExecutor().exec(new CustomCommand());
    }

    @Override
    protected void failed(Throwable e, Description description) {
        super.failed(e, description);
        if (StringUtils.isNotEmpty(SCREENSHOTS_DIR)) {
            String screenShotPath = createScreenShotPath(description);
            new ScreenShooter().saveScreenShotAsPng(screenShotPath);
        }
    }

    private String createScreenShotPath(Description description) {
        String packageName = getPackageNameFromDescription(description);
        String simpleClassName = getSimpleClassNameFromDescription(description);
        String simpleMethodName = description.getMethodName().split("\\W")[0];
        return buildDirectory(packageName) + buildFileName(simpleClassName, simpleMethodName);
    }

    private String getSimpleClassNameFromDescription(Description description) {
        String[] classNameParts = getClassNamePartsFromDescription(description);
        int indexOfSimpleClassName = classNameParts.length - 1;
        return classNameParts[indexOfSimpleClassName];
    }

    private String getPackageNameFromDescription(Description description) {
        String[] classNameParts = getClassNamePartsFromDescription(description);
        return classNameParts[INDEX_OF_TOP_LEVEL_PACKAGE];
    }

    private String[] getClassNamePartsFromDescription(Description description) {
        return description.getClassName().split("\\.");
    }

    private String buildDirectory(String packageName) {
        return SCREENSHOTS_DIR + File.separator + packageName + File.separator;
    }


    private String buildFileName(String simpleClassName, String simpleMethodName) {
        return simpleClassName + HYPHEN + simpleMethodName + PNG_SUFFIX;
    }
}