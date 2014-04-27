package com.digitaslbi.selenium;

import org.junit.Rule;
import org.junit.rules.TestWatcher;

public class BaseTest {
    @Rule public TestWatcher takeScreenshots = new TakeScreenShotsOnFailureWatcher();
    @Rule public TestWatcher createSpecs = new SpecificationLoggerWatcher();
}
