package abstract_factory.buttons.impl;

import abstract_factory.buttons.Button;

public class MacOSButton implements Button {
    @Override
    public void paint() {
        System.out.println("MAC OS BUTTON");
    }
}
