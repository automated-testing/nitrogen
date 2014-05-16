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

    public void threadQuit() {
        try{
            super.quit();
        }
        catch (Exception ex){
            System.out.println("Error while quitting the WebDriver instance");
        }
    }
}
