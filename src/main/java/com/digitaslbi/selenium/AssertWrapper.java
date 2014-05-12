package com.digitaslbi.selenium;

import com.digitaslbi.selenium.common.controls.BaseElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;

public class AssertWrapper {

    private static final Log log = LogFactory.getLog(AssertWrapper.class);

    public static String formatClassName(Object object) {
        return String.format("'%s'", injectSpacesIntoClassNameFor(object.getClass()));
    }

    public static String extractDescription(Object object){
        if(object instanceof BaseElement){
            return ((BaseElement) object).getDescription();
        }
        else {
            return formatClassName(object);
        }
    }

    public static String formatClassName(Class clazz) {
        return String.format("'%s'", injectSpacesIntoClassNameFor(clazz));
    }

    private static String injectSpacesIntoClassNameFor(Class clazz) {
        return clazz.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2");
    }

    private static void logAssert(String message) {}

    private static void logNoMessage() {}

    public static void assertTrue(java.lang.String message, boolean condition) {
        logAssert(message);
        Assert.assertTrue(message, condition);
    }

    public static void assertFalse(java.lang.String message, boolean condition) {
        logAssert(message);
        Assert.assertFalse(message, condition);
    }

    public static void fail(java.lang.String message) {
        logAssert(message);
        Assert.fail(message);
    }

    public static void assertEquals(java.lang.String message, java.lang.Object expected, java.lang.Object actual) {
        logAssert(message);
        Assert.assertEquals(message, expected, actual);
    }

    public static void assertNotEquals(java.lang.String message, java.lang.Object first, java.lang.Object second) {
        logAssert(message);
        Assert.assertNotEquals(message, first, second);
    }

    public static void assertNotEquals(java.lang.String message, long first, long second) {
        logAssert(message);
        Assert.assertNotEquals(message, first, second);
    }

    public static void assertNotEquals(java.lang.String message, double first, double second, double delta) {
        logAssert(message);
        Assert.assertNotEquals(message, first, second, delta);
    }

    public static void assertArrayEquals(java.lang.String message, java.lang.Object[] expecteds, java.lang.Object[] actuals) throws org.junit.internal.ArrayComparisonFailure {
        logAssert(message);
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(java.lang.String message, byte[] expecteds, byte[] actuals) throws org.junit.internal.ArrayComparisonFailure {
        logAssert(message);
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(java.lang.String message, char[] expecteds, char[] actuals) throws org.junit.internal.ArrayComparisonFailure {
        logAssert(message);
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(java.lang.String message, short[] expecteds, short[] actuals) throws org.junit.internal.ArrayComparisonFailure {
        logAssert(message);
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(java.lang.String message, int[] expecteds, int[] actuals) throws org.junit.internal.ArrayComparisonFailure {
        logAssert(message);
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(java.lang.String message, long[] expecteds, long[] actuals) throws org.junit.internal.ArrayComparisonFailure {
        logAssert(message);
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(java.lang.String message, double[] expecteds, double[] actuals, double delta) throws org.junit.internal.ArrayComparisonFailure {
        logAssert(message);
        Assert.assertArrayEquals(message, expecteds, actuals, delta);
    }

    public static void assertArrayEquals(java.lang.String message, float[] expecteds, float[] actuals, float delta) throws org.junit.internal.ArrayComparisonFailure {
        logAssert(message);
        Assert.assertArrayEquals(message, expecteds, actuals, delta);
    }

    public static void assertEquals(java.lang.String message, double expected, double actual, double delta) {
        logAssert(message);
        Assert.assertEquals(message, expected, actual, delta);
    }

    public static void assertEquals(java.lang.String message, float expected, float actual, float delta) {
        logAssert(message);
        Assert.assertEquals(message, expected, actual, delta);
    }

    public static void assertEquals(java.lang.String message, long expected, long actual) {
        logAssert(message);
        Assert.assertEquals(message, expected, actual);
    }

    public static void assertNotNull(java.lang.String message, java.lang.Object object) {
        logAssert(message);
        Assert.assertNotNull(message, object);
    }

    public static void assertNull(java.lang.String message, java.lang.Object object) {
        logAssert(message);
        Assert.assertNull(message, object);
    }

    public static void assertSame(java.lang.String message, java.lang.Object expected, java.lang.Object actual) {
        logAssert(message);
        Assert.assertSame(message, expected, actual);
    }

    public static void assertNotSame(java.lang.String message, java.lang.Object unexpected, java.lang.Object actual) {
        logAssert(message);
        Assert.assertNotSame(message, unexpected, actual);
    }


    public static <T> void assertThat(java.lang.String reason, T actual, org.hamcrest.Matcher<? super T> matcher) {
        logAssert(reason);
        Assert.assertThat(reason, actual, matcher);
    }

    @java.lang.Deprecated
    public static void assertEquals(java.lang.String message, double expected, double actual) {
        logAssert(message);
        Assert.assertEquals(message, expected, actual);
    }

    @java.lang.Deprecated
    public static void assertEquals(java.lang.String message, java.lang.Object[] expecteds, java.lang.Object[] actuals) {
        logAssert(message);
        Assert.assertEquals(message, expecteds, actuals);
    }

    @Deprecated
    public static <T> void assertThat(T actual, org.hamcrest.Matcher<? super T> matcher) {
        logNoMessage();
        Assert.assertThat(actual, matcher);
    }

    @Deprecated
    public static void assertTrue(boolean condition) {
        logNoMessage();
        Assert.assertTrue(condition);
    }

    @Deprecated
    public static void assertFalse(boolean condition) {
        logNoMessage();
        Assert.assertFalse(condition);
    }

    @Deprecated
    public static void fail() {
        logNoMessage();
        Assert.fail();
    }

    @Deprecated
    public static void assertEquals(java.lang.Object expected, java.lang.Object actual) {
        logNoMessage();
        Assert.assertEquals(expected, actual);
    }

    @Deprecated
    public static void assertNotEquals(java.lang.Object first, java.lang.Object second) {
        logNoMessage();
        Assert.assertNotEquals(first, second);
    }

    @Deprecated
    public static void assertNotEquals(long first, long second) {
        logNoMessage();
        Assert.assertNotEquals(first, second);
    }

    @Deprecated
    public static void assertNotEquals(double first, double second, double delta) {
        logNoMessage();
        Assert.assertNotEquals(first, second, delta);
    }

    @Deprecated
    public static void assertArrayEquals(java.lang.Object[] expecteds, java.lang.Object[] actuals) {
        logNoMessage();
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Deprecated
    public static void assertArrayEquals(byte[] expecteds, byte[] actuals) {
        logNoMessage();
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Deprecated
    public static void assertArrayEquals(char[] expecteds, char[] actuals) {
        logNoMessage();
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Deprecated
    public static void assertArrayEquals(short[] expecteds, short[] actuals) {
        logNoMessage();
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Deprecated
    public static void assertArrayEquals(int[] expecteds, int[] actuals) {
        logNoMessage();
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Deprecated
    public static void assertArrayEquals(long[] expecteds, long[] actuals) {
        logNoMessage();
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Deprecated
    public static void assertArrayEquals(double[] expecteds, double[] actuals, double delta) {
        logNoMessage();
        Assert.assertArrayEquals(expecteds, actuals, delta);
    }

    @Deprecated
    public static void assertArrayEquals(float[] expecteds, float[] actuals, float delta) {
        logNoMessage();
        Assert.assertArrayEquals(expecteds, actuals, delta);
    }

    @Deprecated
    public static void assertEquals(long expected, long actual) {
        logNoMessage();
        Assert.assertEquals(expected, actual);
    }

    @java.lang.Deprecated
    public static void assertEquals(double expected, double actual) {
        logNoMessage();
        Assert.assertEquals(expected, actual);
    }

    @Deprecated
    public static void assertEquals(double expected, double actual, double delta) {
        logNoMessage();
        Assert.assertEquals(expected, actual, delta);
    }

    @Deprecated
    public static void assertEquals(float expected, float actual, float delta) {
        logNoMessage();
        Assert.assertEquals(expected, actual, delta);
    }

    @Deprecated
    public static void assertNotNull(java.lang.Object object) {
        logNoMessage();
        Assert.assertNotNull(object);
    }

    @Deprecated
    public static void assertNull(java.lang.Object object) {
        logNoMessage();
        Assert.assertNull(object);
    }

    @Deprecated
    public static void assertSame(java.lang.Object expected, java.lang.Object actual) {
        logNoMessage();
        Assert.assertSame(expected, actual);
    }

    @Deprecated
    public static void assertNotSame(java.lang.Object unexpected, java.lang.Object actual) {
        logNoMessage();
        Assert.assertNotSame(unexpected, actual);
    }

    @java.lang.Deprecated
    public static void assertEquals(java.lang.Object[] expecteds, java.lang.Object[] actuals) {
        logNoMessage();
        Assert.assertEquals(expecteds, actuals);
    }


}
