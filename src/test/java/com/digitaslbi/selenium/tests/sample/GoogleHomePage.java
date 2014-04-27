package com.digitaslbi.selenium.tests.sample;

import com.digitaslbi.selenium.Url;

public class GoogleHomePage implements Url {
    protected String path = "http://www.google.com";
    protected String name = "Google";

    @Override
    public String getPageName() {
        return name;
    }

    @Override
    public String toString() {
        return path;
    }
}
