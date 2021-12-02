package abstract_factory.factories.impl;

import abstract_factory.buttons.Button;
import abstract_factory.buttons.impl.MacOSButton;
import abstract_factory.checkboxes.Checkbox;
import abstract_factory.checkboxes.impl.MacOSCheckbox;
import abstract_factory.factories.AbstractFactory;

public class MacOSFactory implements AbstractFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
