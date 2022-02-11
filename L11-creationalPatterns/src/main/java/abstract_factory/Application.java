package abstract_factory;

import abstract_factory.buttons.Button;
import abstract_factory.checkboxes.Checkbox;
import abstract_factory.factories.AbstractFactory;

public class Application {

    private Button button;
    private Checkbox checkbox;

    public Application(AbstractFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void pressButton() {
        button.paint();
    }

    public void pressCheckbox() {
        checkbox.paint();
    }
}
