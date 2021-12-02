package abstract_factory;

import abstract_factory.factories.AbstractGUIFactory;
import abstract_factory.factories.MacFactory;
import abstract_factory.factories.WindowsFactory;

public class Main {
    public static Application application;

    public static void main(String[] args) {

        application = configureApplication();
        application.pressButton();

    }

    public static Application configureApplication() {
        AbstractGUIFactory factory;
        Application app;

        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacFactory();
            app = new Application(factory);
        } else {
            factory = new WindowsFactory();
            app = new Application(factory);
        }
        return app;
    }
}
