package abstract_factory.factories;

import abstract_factory.buttons.Button;
import abstract_factory.checkboxes.Checkbox;

public interface AbstractFactory {
    Button createButton();
    Checkbox createCheckbox();
}
