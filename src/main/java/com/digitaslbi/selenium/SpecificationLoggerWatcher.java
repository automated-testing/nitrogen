package com.digitaslbi.selenium;

import com.digitaslbi.selenium.webdriver.ScreenShooter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;

public class SpecificationLoggerWatcher extends TestWatcher {
    private static final Log log = LogFactory.getLog(SpecificationLoggerWatcher.class);
    public static final String SPECS_BASE_DIRECTORY = System.getProperty("specs.base.dir");
    protected RollingFileAppender thisTestsAppender;
    protected Logger rootLogger;

    @Override
    protected void starting(Description description) {

        if (isSpecsEnabled()) {
            String simplePackageName = getSimplePackageName(description);
            String specsLocation = SPECS_BASE_DIRECTORY + File.separator + simplePackageName;

            thisTestsAppender = new RollingFileAppender();
            thisTestsAppender.setMaxFileSize("100MB");
            thisTestsAppender.setMaxBackupIndex(0);
            thisTestsAppender.setFile(specsLocation + File.separator + description.getTestClass().getSimpleName()
                    + File.separator + description.getMethodName().split("http")[0] + ".html");
            PatternLayout patternLayout = new PatternLayout();
            patternLayout.setConversionPattern("%m%n");
            thisTestsAppender.setLayout(patternLayout);
            thisTestsAppender.setThreshold(Level.TRACE);
            thisTestsAppender.activateOptions();
            rootLogger = Logger.getRootLogger();
            rootLogger.addAppender(thisTestsAppender);

            log.info("<html><head><title>" + formatMethodName(description) + "</title>" +
                        "<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\"></script>" +
                        "<style>" +
                            ".text{color: green; font-style: italic; font-size: 95%; font-family: tahoma} " +
                        ".link{text-decoration: none; font-weight: normal; font-size: 80%; font-style: italic; color: #33d;} " +
                        "h1{width:60%} li.error{padding-top: 0px} li.error span{background: #e00; color: #fff; padding: 0 20px; margin: 0; display: block;} " +
                        "ol ol li.step{ color: #444; font-size: 95%; font-weight: normal} ol ol " +
                        "li{ list-style-type: lower-latin; line-height:2; width: 60%;border-bottom: 1px dotted #999; padding-top: 15px; color: darkblue} " +
                        ".action{margin-top: 20px;cursor:pointer; font-weight: bold; color: green; } body{ font-family: tahoma; padding: 20px; } ul{line-height: 1.5; padding: 10px 60px; }" +
                        "</style>" +
                    "</head><body>");

            log.info("<h1 style=\"font-family: tahoma\">" + formatMethodName(description) + "</h1><hr/><ol><li class='dummy'><ol>");
        }
    }

    private boolean isSpecsEnabled() {
        return StringUtils.isNotEmpty(SPECS_BASE_DIRECTORY);
    }

    private String getSimplePackageName(Description description) {
        String[] packageNameParts = description.getTestClass().getPackage().getName().split("\\.");
        return packageNameParts[packageNameParts.length - 1];
    }

    private String formatMethodName(Description description) {
        return StringUtils.capitalize(String.format("%s:", description.getMethodName().replaceAll("_", " ")));
    }

    @Override
    protected void finished(Description description) {

        String testEndSnapshotImage = String.format("%s/images/%s.png", SPECS_BASE_DIRECTORY, formatMethodName(description));
        new ScreenShooter().saveScreenShotAsPng(testEndSnapshotImage);

        if(isSpecsEnabled()) {
            log.info("</ol></li></ol>");
            log.info("<img src=\"" +
                    "../../images/" +
                    formatMethodName(description) + ".png" +
                            "\" />");
            log.info("<script>$(function() {" +
                    "console.log('ready'); " +
                    "$('.dummy').remove();" +
                    "$('a').hide();$('ol ol').hide();" +
                    "$('li.action').click(function() {" +
                    "$(this).find('a').toggle(); " +
                    "$(this).next('ol').slideToggle()" +
                    "})" +
                    "});</script></body></html>");
            rootLogger.removeAppender(thisTestsAppender);
        }
    }
}
