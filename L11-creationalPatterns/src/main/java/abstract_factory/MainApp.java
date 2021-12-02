package abstract_factory;

import abstract_factory.factories.AbstractFactory;
import abstract_factory.factories.impl.MacOSFactory;
import abstract_factory.factories.impl.WindowsFactory;

public class MainApp {
    public static Application application;

    public static void main(String[] args) {

        application = configureApplication();
        application.pressButton();
        application.pressCheckbox();
    }

    public static Application configureApplication() {
        AbstractFactory factory;
        Application app;

        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }

        app = new Application(factory);
        return app;
    }
}
