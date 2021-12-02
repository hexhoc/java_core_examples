package abstract_factory.factories;

import abstract_factory.buttons.Button;
import abstract_factory.buttons.MacButton;
import abstract_factory.buttons.WindowsButton;

public class WindowsFactory implements AbstractGUIFactory{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
