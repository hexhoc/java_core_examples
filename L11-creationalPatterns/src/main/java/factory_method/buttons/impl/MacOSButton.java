package factory_method.buttons.impl;

import factory_method.buttons.Button;

public class MacOSButton implements Button {
    @Override
    public void press() {
        System.out.println("MAC OS BUTTON");
    }
}
