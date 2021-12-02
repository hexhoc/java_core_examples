package abstract_factory.factories;

import abstract_factory.buttons.Button;
import abstract_factory.buttons.MacButton;

public class MacFactory implements AbstractGUIFactory{
    @Override
    public Button createButton() {
        return new MacButton();
    }
}
