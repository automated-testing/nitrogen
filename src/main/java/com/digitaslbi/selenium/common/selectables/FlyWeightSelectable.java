package com.digitaslbi.selenium.common.selectables;

import org.openqa.selenium.By;

public interface FlyWeightSelectable extends Selectable{
    By getSecondaryLocator();
}
