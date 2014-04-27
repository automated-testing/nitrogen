package com.digitaslbi.selenium.common.utils;


import com.digitaslbi.selenium.common.controls.BaseElement;


public class EmptyElementFilter implements ElementFilter {

    @Override
    public boolean apply(BaseElement baseElement){
        return false;
    }
}
