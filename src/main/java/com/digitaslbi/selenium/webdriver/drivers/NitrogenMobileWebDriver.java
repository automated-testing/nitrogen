package com.digitaslbi.selenium.webdriver.drivers;

import org.openqa.selenium.firefox.FirefoxProfile;

public class NitrogenMobileWebDriver extends NitrogenFirefoxDriver {
    public NitrogenMobileWebDriver() {
        super(createMobileProfile());
    }

    private static FirefoxProfile createMobileProfile(){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("general.useragent.override", "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A405 Safari/7534.48.3");
        return profile;
    }
}
