package com.digitaslbi.selenium.common.controls;

import com.digitaslbi.selenium.AssertWrapper;
import com.digitaslbi.selenium.common.commands.Command;
import com.digitaslbi.selenium.common.selectables.Selectable;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.is;

public abstract class RadioButton extends BaseElement implements Selectable {

    public RadioButton(WebElement webElement, String description) {
        super(webElement, description);
    }

    public void shouldBeSelected() {
        AssertWrapper.assertThat(String.format("%s should be selected", getDescription()),
                isSelected(),
                is(true));
    }

    @Override
    public void execute(Command command) {
        command.perform(this);
    }

    public void shouldBeDisabled() {
        AssertWrapper.assertThat(String.format("%s should be disabled", getDescription()),
                isEnabled(),
                is(false));
    }

}
