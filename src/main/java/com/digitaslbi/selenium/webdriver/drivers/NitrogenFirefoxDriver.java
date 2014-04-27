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
     * The exception is being caught here as a temporary measure as the javascript core metrics
     * library is sending an HTTP request we can't explicitly wait until it completes as the
     * core metrics API doesn't seem to be using the main jQuery object that the rest of the page
     * uses.
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
