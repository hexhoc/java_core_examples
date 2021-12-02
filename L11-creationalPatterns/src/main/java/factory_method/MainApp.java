package factory_method;

import factory_method.buttons.Button;
import factory_method.factories.impl.MacOSFactory;
import factory_method.factories.impl.WindowsFactory;
import factory_method.factories.AbstractFactory;

public class MainApp {
    public static void main(String[] args) {
        AbstractFactory factory = createFactoryByOS();
        Button button = factory.createButton();
        doSomethingWithButton(button);
    }

    public static void doSomethingWithButton(Button button) {
        button.press();
        button.press();
        button.press();
        button.press();
        button.press();
        button.press();
        button.press();
        button.press();
        button.press();
    }

    public static AbstractFactory createFactoryByOS() {
        AbstractFactory factory;

        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }

        return factory;
    }
}
