package com.digitaslbi.selenium.webdriver.configuration;

public class WaitConfiguration {

    public static long timeout;

    static {
        timeout = System.getProperty("selenium.waitvalue") == null ? 1 : Long
                .parseLong(System.getProperty("selenium.waitvalue"));
    }

}
