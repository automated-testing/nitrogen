package com.digitaslbi.selenium.webdriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenShooter {

    public void saveScreenShotAsPng(String fileName) {
        TakesScreenshot driver = RemoteWebDriverFactory.screenshotInstance();
        try {
            File screenShotPng = driver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShotPng, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
