package abstract_factory;

import abstract_factory.buttons.Button;
import abstract_factory.factories.AbstractGUIFactory;
import abstract_factory.factories.MacFactory;
import abstract_factory.factories.WindowsFactory;

public class Application {

    private Button button;

    public Application(AbstractGUIFactory factory) {
        button = factory.createButton();
    }

    public void pressButton() {
        button.paint();
    }
}
