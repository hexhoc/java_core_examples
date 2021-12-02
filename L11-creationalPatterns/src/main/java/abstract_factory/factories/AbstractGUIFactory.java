package abstract_factory.factories;

import abstract_factory.buttons.Button;

public interface AbstractGUIFactory {
    Button createButton();
}
