package com.digitaslbi.selenium.webdriver.drivers;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class NitrogenFirefoxDriver extends FirefoxDriver implements Quitable {

    @Override
    public void quit() {

    }

    public NitrogenFirefoxDriver() {
        super();
    }

    public NitrogenFirefoxDriver(FirefoxProfile profile) {
        super(profile);
    }

    /**
     * TODO
     * Catching any unfinished AJAX requests that are still pending after the test has finised (non-jQuery)
     * More robust solution should be found.
     */
    public void threadQuit() {
        try{
            super.quit();
        }
        catch (Exception ex){
            System.out.println("Error while quitting the WebDriver instance");
        }
    }
}
