package com.digitaslbi.selenium;

import com.digitaslbi.selenium.common.controls.BaseElement;

public class ClassNameFormatter {
    public String extractDescription(final Object object) {
        if(object instanceof BaseElement){
            return ((BaseElement) object).getDescription();
        }
        else {
            return formatClassName(object.getClass());
        }
    }

    public String injectSpacesIntoCamelCasedName(String className) {
        return className.replaceAll("([a-z])([A-Z])", "$1 $2");
    }

    public String formatClassName(Class clazz) {
        return String.format("'%s'", injectSpacesIntoCamelCasedName(clazz.getSimpleName()));
    }

}
