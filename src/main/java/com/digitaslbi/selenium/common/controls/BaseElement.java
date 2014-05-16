package com.digitaslbi.selenium.common.controls;


import com.digitaslbi.selenium.AssertWrapper;
import com.digitaslbi.selenium.ClassNameFormatter;
import com.digitaslbi.selenium.Failures;
import com.digitaslbi.selenium.webdriver.RemoteWebDriverFactory;
import com.digitaslbi.selenium.webdriver.Wait;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.digitaslbi.selenium.AssertWrapper.assertThat;
import static com.digitaslbi.selenium.Failures.failOnFound;
import static com.digitaslbi.selenium.Failures.failOnNotFound;
import static org.hamcrest.CoreMatchers.is;

public class BaseElement {

    private String currentElementDescription;
    private static final Log log = LogFactory.getLog(BaseElement.class);
    private ClassNameFormatter classNameFormatter = new ClassNameFormatter();


    /**
     * TODO
     * Need to remove the driver instance from BaseElement. Any sub-classes that are using it should
     * resort to the ElementsBuilder instead to create elements.
     */
    protected RemoteWebDriver driver = RemoteWebDriverFactory.instance();

    private String url;

    public static final String LOCATOR_FIELD = "LOCATOR";
    public static final String DESC_FIELD = "DESCRIPTION";
    public static final String OF = " of ";
    public static final String IN = " in ";
    public static final String THE = "The ";
    public static final String FIELD_IN = "field in ";
    public static final String WITHIN = " within ";
    private WebElement currentElement;
    protected Wait wait = new Wait();

    public BaseElement(WebElement webElement, String description) {
        this.currentElement = webElement;
        this.currentElementDescription = description;
    }

    /**
     * This constructor should only be used to create component-level elements or any
     * floating elements that don't necessarily have a parent element.
     *
     * Use ElementsBuilder.buildComponent() instead
     */
    @Deprecated
    public BaseElement(By by, String description) {
        try {
            waitUntilElementIsAvailable(by, description);
            this.currentElement = RemoteWebDriverFactory.instance().findElement(by);
            this.currentElementDescription = description;
        } catch (Exception e) {
            failOnNotFound(description);
        }
    }

    /**
     * This constructor should only be used to create child-level elements.
     * Use ElementsBuilder.buildChild() instead
     */
    @Deprecated
    public BaseElement(By by, String childDescription, BaseElement parent) {
        this.currentElementDescription = THE + childDescription + OF + parent.getDescription();
        try {
            waitUntilControlIsVisible(parent.getWebElement().findElement(by), childDescription);
            this.currentElement = parent.getWebElement().findElement(by);
        } catch (Exception e) {
            failOnNotFound(currentElementDescription);
        }
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    /**
     * TODO:
     * Should be separated from regular wait.
     * Only applicable to controls, which have to be in the page & waiting for visibility.
     */
    protected void waitUntilControlIsVisible(WebElement element, String description) {
        wait.untilControlIsVisible(element, description);
    }

    protected void waitUntilElementIsAvailable(By by, String description) {
        wait.untilElementIsDisplayed(by, description);
    }

    public void exists() {
        assertThat(String.format("%s should be present", getDescription()), isDisplayed(), is(true));
    }


    public <T extends BaseElement> boolean hasChildElement(Class<T> clazz) {
        By by = null;
        try {
            Field locator = clazz.getField(LOCATOR_FIELD);
            by = (By) locator.get(By.class);
        } catch (Exception e) {
            failOnNotFound(clazz.getSimpleName() + IN + getDescription());
        }
        try {
            return isPresentAndDisplayed(by);
        } catch (WebDriverException e) {
            throw new InvalidSelectorFormatException(clazz, by, e);
        }
    }

    public  <T extends BaseElement> void hasAtLeastOneChildElement(Class<T>... classes) {

    }

    public <T extends BaseElement> void shouldHaveChildElement(Class<T> clazz) {
        AssertWrapper.assertThat("Should have a " + classNameFormatter.formatClassName(clazz) + IN + getDescription(), hasChildElement(clazz), is(true));
        if (!hasChildElement(clazz)) {
            failOnNotFound(clazz.getSimpleName() + IN + getDescription());
        }
    }

    private <T extends BaseElement> String getLocatorDescriptionFrom(Class<T> clazz) {
        return FIELD_IN + clazz.getSimpleName() + IN + getDescription();
    }

    public <T extends BaseElement> void shouldNotHaveChildElement(Class<T> clazz) {
        AssertWrapper.assertThat(String.format("Should not have a %s%s%s", clazz.getSimpleName(), IN, getDescription()), hasChildElement(clazz), is(false));
        if (hasChildElement(clazz)) {
            failOnFound(getLocatorDescriptionFrom(clazz));
        }
    }

    private boolean isPresentAndDisplayed(By by) {
        List<WebElement> elements = RemoteWebDriverFactory.instance().findElements(by);
        return hasAtLeastOneElement(elements) && atLeastOneElementIsDisplayed(elements);
    }

    private boolean atLeastOneElementIsDisplayed(List<WebElement> elements) {
        for(WebElement element : elements){
            if(element.isDisplayed()){
                return true;
            }
        }
        return false;
    }

    private boolean hasAtLeastOneElement(List<WebElement> elements) {
        return (elements.size() > 0);
    }


    /**
     * @deprecated Use the createChildElement method instead
     */
    @Deprecated
    public BaseElement getChildElement(By by, String description) {
        try {
            WebElement webElement = currentElement.findElement(by);
            return new BaseElement(webElement, description + OF + getDescription());
        } catch (Exception e) {
            failOnNotFound(description + WITHIN + getDescription());
            return null;
        }
    }

    public List<BaseElement> getChildElements(By by, String description) {
        try {
            List<BaseElement> baseElements = new ArrayList<BaseElement>();
            List<WebElement> webElementList = currentElement.findElements(by);
            for (WebElement element : webElementList) {
                baseElements.add(new BaseElement(element, description));
            }
            return baseElements;
        } catch (Exception e) {
            failOnNotFound(description + WITHIN + getDescription());
            return null;
        }
    }

    public WebElement getWebElement() {
        return currentElement;
    }

    public void submit() {
        currentElement.submit();
        wait.untilPageUrlHasChanged(getUrl());
    }

    public void sendKeys(CharSequence... keysToSend) {
        RemoteWebDriverFactory.instance().executeScript(
                "$('#" + currentElement.getAttribute("id") + "').trigger('focus')"
        );
        currentElement.sendKeys(keysToSend);
    }

    public void click() {
        currentElement.click();
    }

    public void clear() {
        currentElement.clear();
    }

    public String getText() {
        return currentElement.getText();
    }

    public boolean isDisplayed() {
        return currentElement.isDisplayed();
    }

    public String getCssAttribute(String cssAttribute) {
        return currentElement.getCssValue(cssAttribute);
    }

    public String getDescription() {
        return currentElementDescription;
    }

    public String getFailureDescription() {
        return Failures.formatDescriptionOnFail(currentElementDescription);
    }

    public String getAttribute(String attributeName) {
        return currentElement.getAttribute(attributeName);
    }

    public boolean isSelected() {
        return currentElement.isSelected();
    }

    public boolean isEnabled() {
        return currentElement.isEnabled();
    }

    public WebElement findElement(By by) {
        return currentElement.findElement(by);
    }

    public List<WebElement> findElements(By by) {
        return currentElement.findElements(by);
    }

    public void shouldHaveAtLeastOneChildElement(List<Class<? extends BaseElement>> list) {
        for(Class<? extends BaseElement> clazz : list){
            if(hasChildElement(clazz)){
               return;
            }
        }
        AssertWrapper.fail(String.format("Unable to locate any of the elements %s in %s", list, getDescription()));
    }


    public void closeWindow() {
        RemoteWebDriverFactory.closeCurrentWindow();
    }
}
