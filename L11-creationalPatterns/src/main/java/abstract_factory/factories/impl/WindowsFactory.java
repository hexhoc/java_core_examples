package abstract_factory.factories.impl;

import abstract_factory.buttons.Button;
import abstract_factory.buttons.impl.WindowsButton;
import abstract_factory.checkboxes.Checkbox;
import abstract_factory.checkboxes.impl.MacOSCheckbox;
import abstract_factory.checkboxes.impl.WindowsCheckbox;
import abstract_factory.factories.AbstractFactory;

public class WindowsFactory implements AbstractFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }

}
