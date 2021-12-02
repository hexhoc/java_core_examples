package factory_method.factories.impl;

import factory_method.buttons.Button;
import factory_method.buttons.impl.WindowsButton;
import factory_method.factories.AbstractFactory;

public class WindowsFactory implements AbstractFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
