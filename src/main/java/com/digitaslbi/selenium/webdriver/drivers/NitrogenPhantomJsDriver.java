package com.digitaslbi.selenium.webdriver.drivers;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;

public class NitrogenPhantomJsDriver extends PhantomJSDriver implements Quitable {

    public static final String WEB_SECURITY_ARG = "web-security";
    public static final String SSL_PROTOCOL_ARG = "ssl-protocol";
    public static final String IGNORE_SSL_ERRORS_ARG = "ignore-ssl-errors";
    public static final String LOG_LEVEL_ARG = "logLevel";
    public static final String ANY = "any";
    public static final String ERROR = "ERROR";
    public static final String FALSE = Boolean.FALSE.toString();
    public static final String TRUE = Boolean.TRUE.toString();
    private static final String PHANTOM_JS_PATH_PROP = System.getProperty("phantomjs.binary.path");

    private static String formatArgument(String argName, String argValue) {
        return String.format("--%s=%s", argName, argValue);
    }

    public NitrogenPhantomJsDriver() {
        super(initBrowserCapabilities());
    }

    private static DesiredCapabilities initBrowserCapabilities() {
        DesiredCapabilities browserCapabilities = new DesiredCapabilities();

        browserCapabilities.setJavascriptEnabled(true);
        if (StringUtils.isNotEmpty(PHANTOM_JS_PATH_PROP)) {
            System.out.printf("\n\nSetting Phantom JS path to %s\n\n%n", PHANTOM_JS_PATH_PROP);
            browserCapabilities.setCapability(
                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    PHANTOM_JS_PATH_PROP);
        }
        browserCapabilities.setCapability("takesScreenshot", true);
        browserCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, buildPhantomJsCommandLineArguments());
        browserCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS, new String[]{
                formatArgument(LOG_LEVEL_ARG, ERROR)
        });

        return browserCapabilities;
    }

    private static List<String> buildPhantomJsCommandLineArguments() {
        List<String> commandLineArguments = new ArrayList<String>();
        commandLineArguments.add(formatArgument(WEB_SECURITY_ARG, FALSE));
        commandLineArguments.add(formatArgument(SSL_PROTOCOL_ARG, ANY));
        commandLineArguments.add(formatArgument(IGNORE_SSL_ERRORS_ARG, TRUE));
        return commandLineArguments;
    }

    @Override
    public void quit() {

    }

    public void threadQuit() {
        try{
            super.quit();
        }
        catch (Exception ex){
            System.out.println("Error while quitting the WebDriver instance");
        }
    }

}
