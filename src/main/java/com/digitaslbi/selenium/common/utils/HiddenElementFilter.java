package com.digitaslbi.selenium.common.utils;


import com.digitaslbi.selenium.common.controls.BaseElement;

public class HiddenElementFilter implements ElementFilter {

    @Override
    public boolean apply(BaseElement baseElement){
        if (baseElement.getCssAttribute("display").equalsIgnoreCase("none")){
            return true;
        }
        return false;
    }
}
