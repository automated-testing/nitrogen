package com.digitaslbi.selenium.common.utils.aspects;

import com.digitaslbi.selenium.AssertWrapper;
import com.digitaslbi.selenium.Url;
import com.digitaslbi.selenium.common.controls.BaseElement;
import com.digitaslbi.selenium.webdriver.ScreenShooter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;
import java.util.Date;

public aspect SpecBuilderAspect {

    private static final Log log = LogFactory.getLog(SpecBuilderAspect.class);
    public static final String LI_CLASS_ACTION = "<li class='action'>";
    public static final String LI_CLASS_STEP = "<li class='step'>";
    public static final String LI_CLASS_ERROR = "<li class='error'>";
    public static final String CLOSE_LI = "</li>";
    public static final String CLOSE_LI_CLOSE_OL = CLOSE_LI + "</ol>";
    public static final String CLOSE_LI_OPEN_OL = CLOSE_LI + "<ol>";
    public static final String OPEN_LI = "<li>";
    public static final String SPACE = " ";

    pointcut logAssertMessage(String message):
            call(static void com.digitaslbi.selenium.AssertWrapper.logAssert(..)) && args(message);

    pointcut logNoMessage():
            call(static void com.digitaslbi.selenium.AssertWrapper.logNoMessage());

    pointcut openPage(Url url):
            execution(static void com.digitaslbi.selenium.webdriver.RemoteWebDriverFactory.visit(..)) && args(url);

    pointcut baseElementSubmit(Object object):
        execution(void com.digitaslbi.selenium.common.controls.BaseElement+.submit()) && target(object);

    pointcut baseElementClear(Object object):
        execution(void com.digitaslbi.selenium.common.controls.BaseElement+.clear()) && target(object);

    pointcut baseElementClick(Object object):
        execution(void com.digitaslbi.selenium.common.controls.BaseElement+.click()) && target(object);

    pointcut baseElementSendKeys(Object object, CharSequence[] keysToSend):
        execution(void com.digitaslbi.selenium.common.controls.BaseElement+.sendKeys(..)) && target(object) && args(keysToSend);

    pointcut baseElementShould(Object object):
        (execution(* com.digitaslbi.selenium..*has*(..)) || execution(* com.digitaslbi.selenium..*should*(..))) && target(object);

    pointcut baseElementExist(BaseElement baseElement):
        (execution(* com.digitaslbi.selenium..*exists*(..))) && target(baseElement);

    before(Object object): baseElementShould(object) {
        log.info(String.format("%s%s %s.%s", OPEN_LI, AssertWrapper.extractDescription(object), injectSpacesIntoMethodNameFor(thisJoinPoint), CLOSE_LI));
    }

    pointcut testShould():
        execution(void com.digitaslbi.selenium.customerservice.CustomerServiceTest.select_booked_flight_from_any_other_questions_page_and_submit_invalid_data());

    before(): testShould() {
        log.info(String.format("%s%s.%s", OPEN_LI, injectSpacesIntoMethodNameFor(thisJoinPoint), CLOSE_LI));
    }

    private static String injectSpacesIntoMethodNameFor(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName().replaceAll("([a-z])([A-Z])", "$1 $2").toLowerCase();
    }

    before(Url url): openPage(url) {
        log.info(CLOSE_LI_CLOSE_OL + LI_CLASS_ACTION + "Open " + url.getPageName() + "<br/><a class='link' href='" + url.toString() + "'>[" + url.toString() + "]</a>" + CLOSE_LI_OPEN_OL);
    }

    before(String message): logAssertMessage(message) {
       log.info(OPEN_LI + message + CLOSE_LI);
    }

    before(): logNoMessage() {
        log.info(LI_CLASS_ERROR + "<span>SOME ASSERT WITHOUT ANY MESSAGE</span>" + CLOSE_LI);
    }

    before(Object object, CharSequence[] keysToSend): baseElementSendKeys(object, keysToSend) {
        log.info(LI_CLASS_STEP + "Type <span class='text'>  " + Arrays.toString(keysToSend) + "  </span> in " + ((BaseElement) object).getDescription() + CLOSE_LI);
    }

    before(Object object): baseElementSubmit(object) {
        logAction(object, "Submit");
    }

    before(Object object): baseElementClear(object) {
        logStep(object, "Clear");
    }

    before(Object object): baseElementClick(object) {
        logStep(object, "Click");
    }

    after(BaseElement baseElement): baseElementExist(baseElement) {
        log.info(String.format("%s%s%s%s%s", LI_CLASS_STEP, getDescription(baseElement), SPACE, "should exist", CLOSE_LI));
        String imageFileName = "/snapshot_" + new Date().getTime() + ".png";
        String imagePath = System.getProperty("specs.base.dir") + "/images/" + imageFileName;
        new ScreenShooter().saveScreenShotAsPng(imagePath);
        log.info("<img src=\"" + "../../images/" + imageFileName + "\" />");
    }

    private void logStep(Object object, String stepName) {
        log.info(String.format("%s%s%s%s%s", LI_CLASS_STEP, stepName, SPACE, getDescription(object), CLOSE_LI));
    }

    private void logAction(Object object, String description) {
        log.info(String.format("%s%s%s%s%s%s", CLOSE_LI_CLOSE_OL, LI_CLASS_ACTION, description, SPACE, getDescription(object), CLOSE_LI_OPEN_OL));
    }

    private String getDescription(Object object) {
        return ((BaseElement) object).getDescription();
    }

}
