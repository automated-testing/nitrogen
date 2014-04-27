package com.digitaslbi.selenium.common.utils;

import com.digitaslbi.selenium.Failures;
import com.digitaslbi.selenium.common.controls.BaseElement;
import com.digitaslbi.selenium.common.exceptions.MissingConstructorDeclarationException;
import com.digitaslbi.selenium.common.exceptions.MissingFieldDeclarationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TODO
 * Re-visit the exception handling logic in general for the whole framework as well as
 * producing meaningful error messages when we are unable to find an element when we have
 * a wrong locator or the case in which that the wanted element is not present (for some reason)
 * in the DOM.
 */
public class ElementsBuilder {

    private WaitWrapper waitWrapper;
    private ElementsFinder elementsFinder;

    public ElementsBuilder(){
        waitWrapper = new WaitWrapper();
        elementsFinder = new ElementsFinder();
    }

    public <T extends BaseElement>T buildComponent(Class<T> clazz){
        By locator = getLocatorField(clazz);
        String description = getDescriptionField(clazz);

        waitWrapper.untilElementIsDisplayed(locator, description);
        WebElement element = elementsFinder.findComponentElement(locator);

        return instantiate(clazz, description, element);
    }

    public <T extends BaseElement>T buildNonVisibleComponent(Class<T> clazz){
        By locator = getLocatorField(clazz);
        String description = getDescriptionField(clazz);

        waitWrapper.untilElementIsPresent(locator, description);
        WebElement element = elementsFinder.findComponentElement(locator);

        return instantiate(clazz, description, element);
    }

    public <T extends BaseElement>T buildChild(Class<T> clazz, BaseElement parent){
        By locator = getLocatorField(clazz);
        String childDescription = getDescriptionField(clazz);

        WebElement element = findElement(parent, locator, childDescription);

        return instantiate(clazz, String.format("The %s of %s", childDescription, parent.getDescription()), element);
    }

    public <T extends BaseElement> List<T> buildChildren(Class<T> clazz, BaseElement parent, ElementFilter filter){
        By locator = getLocatorField(clazz);
        String childDescription = getDescriptionField(clazz);

        List<WebElement> elements = findElements(parent, locator, childDescription,filter);

        List<T> builtElements = new ArrayList<T>();
        for (WebElement element : elements) {
            builtElements.add(instantiate(clazz, String.format("The %s of %s", childDescription, parent.getDescription()), element));
        }

        return builtElements;
    }

    private <T extends BaseElement> void shouldNotHave(Class<T> clazz) {
        if(elementsFinder.doesElementExist(getLocatorField(clazz))){
            Failures.failOnFound(getDescriptionField(clazz));
        }
    }

    private WebElement findElement(BaseElement parent, By locator, String childDescription) {
        WebElement element = elementsFinder.findChildElement(locator, parent.getWebElement());
        waitWrapper.untilControlIsVisible(element, childDescription);
        return element;
    }

    private List<WebElement> findElements(BaseElement parent, By locator, String childDescription, ElementFilter filter) {
        List<WebElement> elements = elementsFinder.findChildElements(locator, parent.getWebElement());
        Iterator<WebElement> iterator = elements.iterator();
        while (iterator.hasNext()){
            WebElement webElement = iterator.next();
            BaseElement baseElement = new BaseElement(webElement, childDescription);
            if (filter.apply(baseElement)){
                iterator.remove();
                continue;
            }
            waitWrapper.untilControlIsVisible(webElement, childDescription);
        }
        return elements;
    }

    private <T extends BaseElement> T instantiate(Class<T> clazz, String description, WebElement element) {
        Exception exception;
        try {
            T instance = clazz.getConstructor(WebElement.class, String.class).newInstance(element, description);
            return initInstance(instance);
        } catch (InstantiationException e) {
            exception = e;
        } catch (IllegalAccessException e) {
            exception = e;
        } catch (InvocationTargetException e) {
            exception = e;
        } catch (NoSuchMethodException e) {
            exception = e;
        }
        throw new MissingConstructorDeclarationException(exception, clazz);
    }

    private <T extends BaseElement> T initInstance(T instance) {
        instance.setUrl(elementsFinder.getCurrentUrl());
        return instance;
    }

    private <T extends BaseElement> String getDescriptionField(Class<T> clazz) {
        try {
            String descriptionFromAnnotation = clazz.getAnnotation(Description.class).value();
            return (descriptionFromAnnotation == null)
                ? (String) clazz.getField(BaseElement.DESC_FIELD).get(By.class)
                : descriptionFromAnnotation;
        } catch (Exception e) {
            throw new MissingFieldDeclarationException(BaseElement.DESC_FIELD, e, clazz);
        }
    }

    private <T extends BaseElement> By getLocatorField(Class<T> clazz) {
        try {
            By byFromAnnotation = readByFromAnnotation(clazz);
            return (byFromAnnotation == null)
                ? (By) clazz.getField(BaseElement.LOCATOR_FIELD).get(By.class)
                : byFromAnnotation;
        } catch (Exception e) {
            throw new MissingFieldDeclarationException(BaseElement.LOCATOR_FIELD, e, clazz);
        }
    }

    private <T extends BaseElement>  By readByFromAnnotation(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        LocatedBy locatedBy = clazz.getAnnotation(LocatedBy.class);
        for (Method method : By.class.getDeclaredMethods()) {
            if (returnsByClass(method)) {
                String value = getEquivalentValueFromLocatedBy(locatedBy, method);
                if (!value.equals("")) {
                    return (By) method.invoke(By.class, value);
                }
            }
        }
        return null;
    }

    private String getEquivalentValueFromLocatedBy(final LocatedBy locatedBy, final Method method) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method relatedAnnotationValueMethod = LocatedBy.class.getDeclaredMethod(method.getName());
        return (String) relatedAnnotationValueMethod.invoke(locatedBy, null);
    }

    private boolean returnsByClass(final Method method) {
        return method.getReturnType().equals(By.class);
    }

    public void setWaitWrapper(WaitWrapper waitWrapper) {
        this.waitWrapper = waitWrapper;
    }

    public void setElementsFinder(ElementsFinder elementsFinder) {
        this.elementsFinder = elementsFinder;
    }

    public static <T extends BaseElement> T the(Class<T> clazz) {
        return new ElementsBuilder().buildComponent(clazz);
    }

    public static <T extends BaseElement> T the(Class<T> clazz, BaseElement parent) {
        return new ElementsBuilder().buildChild(clazz, parent);
    }

    public static <T extends BaseElement> T buildComponentElement(Class<T> clazz) {
        return new ElementsBuilder().buildComponent(clazz);
    }

    public static <T extends BaseElement> T buildNonVisibleComponentElement(Class<T> clazz) {
        return new ElementsBuilder().buildNonVisibleComponent(clazz);
    }


    public static <T extends BaseElement> T buildChildElement(Class<T> clazz, BaseElement parent) {
        return new ElementsBuilder().buildChild(clazz, parent);
    }

    public static <T extends BaseElement> List<T> buildChildElements(Class<T> clazz, BaseElement parent) {
        return new ElementsBuilder().buildChildren(clazz, parent, new EmptyElementFilter());
    }

    public static <T extends BaseElement> void shouldNotExist(Class<T> clazz) {
        new ElementsBuilder().shouldNotHave(clazz);
    }

    public static <T extends BaseElement> void shouldNotDisplay(Class<T> clazz) {
        new ElementsBuilder().shouldNotHave(clazz);
    }

    public static  <T extends BaseElement> List<T> buildChildElementsIgnoreHidden(Class<T> clazz, BaseElement parent) {
        return new ElementsBuilder().buildChildren(clazz, parent, new HiddenElementFilter() );
    }
}