package factory_method;

import factory_method.factory.NotificationFactory;
import factory_method.model.Notification;

public class MainApp {
    public static void main(String[] args) {
        NotificationFactory factory = new NotificationFactory();
        Notification notification = factory.createNotification("SMS");
        notification.notifyUser();
    }
}
