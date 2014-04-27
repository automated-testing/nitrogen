package com.digitaslbi.selenium.webdriver;

import com.digitaslbi.selenium.Url;
import com.digitaslbi.selenium.webdriver.configuration.WaitConfiguration;
import com.digitaslbi.selenium.webdriver.drivers.*;
import com.digitaslbi.selenium.webdriver.drivers.NitrogenMobileWebDriver;
import com.digitaslbi.selenium.webdriver.drivers.NitrogenFirefoxDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * TODO
 * As this class is meant to control the creation of instances of WebDriver created throughout the framework, there
 * is a subtle bug in it which enables the creation of more than one instance of WebDriver if the client code invokes
 * the initBrowser() method directly. This need to be fixed.
 */
public class RemoteWebDriverFactory {

    public static final String PHANTOMJS = "phantomjs";
    public static final String NITROGEN_WEB_DRIVER = "web.driver";
    private static final String NITROGEN_MOBILE = "mobile";
    private static RemoteWebDriver instance;

    static {
        initBrowser();
    }

    private static Url initUrl;

    private static final int NEW_WINDOW = 1;
    private static final int OLD_WINDOW = 0;

    public static WebDriverWait waiting() {
        return new WebDriverWait(RemoteWebDriverFactory.instance(), WaitConfiguration.timeout);
    }

    public static RemoteWebDriver instance() {
        return instance;
    }

    public static TakesScreenshot screenshotInstance() {
        if(instance instanceof TakesScreenshot){
            return (TakesScreenshot) instance;
        }
        throw new IllegalArgumentException("The used web driver instance does not support screenshots");
    }

    public static void initBrowser() {
        instance = buildInstance();
        Runtime.getRuntime().addShutdownHook(new BrowserShutDownHook());
    }

    private static RemoteWebDriver buildInstance() {
        String webDriver = System.getProperty(NITROGEN_WEB_DRIVER);
        if(PHANTOMJS.equals(webDriver)) {
            return new NitrogenPhantomJsDriver();
        }
        else if(NITROGEN_MOBILE.equals(webDriver)){
            return new NitrogenMobileWebDriver();
        }
        else{
            return new NitrogenFirefoxDriver();
        }
    }

    public static void visit(Url url) {
        RemoteWebDriverFactory.initUrl = url;
        instance.get(url.toString());
    }

    public static void reset() {
        instance().manage().deleteAllCookies();
        instance().get(RemoteWebDriverFactory.initUrl.toString());
    }

    public static void closeCurrentWindow() {
        instance().close();
        switchToOldWindow();
    }

    private static void switchToOldWindow() {
        if(RemoteWebDriverFactory.instance().getWindowHandles().size() > 0){
            instance().switchTo().window(getWindowsHandles().get(OLD_WINDOW));
        }
    }

    public static void switchToNewWindow() {
        instance().switchTo().window(getWindowsHandles().get(NEW_WINDOW));
    }

    private static ArrayList<String> getWindowsHandles() {
        ArrayList<String> windowHandles = new ArrayList<String>();
        windowHandles.addAll(instance().getWindowHandles());
        return windowHandles;
    }

    static class BrowserShutDownHook extends Thread {

        @Override
        public void run() {
            ((Quitable)instance).threadQuit();
        }
    }
}
