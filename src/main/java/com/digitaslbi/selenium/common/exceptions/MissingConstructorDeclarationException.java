package com.digitaslbi.selenium.common.exceptions;

public class MissingConstructorDeclarationException extends RuntimeException {
    public MissingConstructorDeclarationException(Throwable cause, Class clazz){
        super(String.format("A publicly accessible constructor %s(WebElement webElement, String description) must be defined in %s", clazz.getSimpleName(), clazz.getName()), cause);
    }
}
