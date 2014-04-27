package com.digitaslbi.selenium.common.exceptions;

public class MissingFieldDeclarationException extends RuntimeException{
    public MissingFieldDeclarationException(String fieldName, Throwable cause, Class clazz) {
        super(String.format("A publicly accessible %s field must be defined in %s" +
                "\n(Also ensure that this is static, so that it can be accessed via reflection)", fieldName, clazz.getName()), cause);
    }
}
