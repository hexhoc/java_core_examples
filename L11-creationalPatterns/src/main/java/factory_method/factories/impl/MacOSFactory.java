package factory_method.factories.impl;

import factory_method.buttons.Button;
import factory_method.buttons.impl.MacOSButton;
import factory_method.factories.AbstractFactory;

public class MacOSFactory implements AbstractFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }
}
