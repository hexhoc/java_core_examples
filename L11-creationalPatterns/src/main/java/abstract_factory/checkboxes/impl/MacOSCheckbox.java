package abstract_factory.checkboxes.impl;

import abstract_factory.checkboxes.Checkbox;

public class MacOSCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("MAC OS CHECKBOX");
    }
}
