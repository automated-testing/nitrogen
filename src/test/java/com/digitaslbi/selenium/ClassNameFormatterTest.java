package com.digitaslbi.selenium;

import com.digitaslbi.selenium.common.controls.BaseElement;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ClassNameFormatterTest {
    private ClassNameFormatter formatter = new ClassNameFormatter();
    @Test
    public void test_that_a_base_element_will_return_its_description_rather_than_its_class_name() throws Exception {
        assertThat( "Should return the description of the BaseElement",
                    formatter.extractDescription(new Dog()),
                    is(equalTo("This is a dog")));
    }

    @Test
    public void test_that_an_object_that_isnt_an_instance_of_base_element_will_return_its_own_name() throws Exception {
        assertThat("Should return a title-case version of the class name in single quotes",
                formatter.extractDescription(new SaberToothedTiger()),
                is(equalTo("'Saber Toothed Tiger'")));
    }

    class SaberToothedTiger {}

    class Dog extends BaseElement {
        public Dog() {
            super((WebElement) null, null);
        }
        
        @Override
        public String getDescription() {
            return "This is a dog";
        }
    }
}
