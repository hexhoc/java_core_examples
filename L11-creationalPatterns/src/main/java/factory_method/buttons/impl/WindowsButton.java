package factory_method.buttons.impl;

import factory_method.buttons.Button;

public class WindowsButton implements Button {

    @Override
    public void press() {
        System.out.println("WINDOWS BUTTON");
    }
}
