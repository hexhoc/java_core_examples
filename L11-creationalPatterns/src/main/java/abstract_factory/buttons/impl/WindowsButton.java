package abstract_factory.buttons.impl;

import abstract_factory.buttons.Button;

public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("WINDOWS BUTTON");
    }
}
