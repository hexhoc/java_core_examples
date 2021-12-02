package abstract_factory.checkboxes.impl;

import abstract_factory.checkboxes.Checkbox;

public class WindowsCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("WINDOWS CHECKBOX");
    }
}
