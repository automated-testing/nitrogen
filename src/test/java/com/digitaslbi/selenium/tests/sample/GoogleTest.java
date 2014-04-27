package com.digitaslbi.selenium.tests.sample;

import com.digitaslbi.selenium.Url;
import com.digitaslbi.selenium.sample.SearchBox;
import org.junit.Test;

import static com.digitaslbi.selenium.AssertWrapper.assertThat;
import static com.digitaslbi.selenium.common.utils.ElementsBuilder.buildComponentElement;
import static com.digitaslbi.selenium.webdriver.RemoteWebDriverFactory.visit;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class GoogleTest {

    private final Url homepage = new GoogleHomePage();

    @Test
    public void test_that_I_can_connect_to_Google() throws Exception {
        visit(homepage);

        SearchBox searchBox = buildComponentElement(SearchBox.class);
        assertThat("Search Box should be displayed",
                searchBox.isDisplayed(),
                is(equalTo(true)));
    }

}
