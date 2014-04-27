package com.digitaslbi.selenium.common.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface LocatedBy {
    public String className() default "";
    public String cssSelector() default "";
    public String id() default "";
    public String linkText() default "";
    public String name() default "";
    public String partialLinkText() default "";
    public String tagName() default "";
    public String xpath() default "";
}
