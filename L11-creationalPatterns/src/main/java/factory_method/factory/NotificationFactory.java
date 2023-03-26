package factory_method.factory;

import factory_method.model.EmailNotification;
import factory_method.model.Notification;
import factory_method.model.PushNotification;
import factory_method.model.SmsNotification;

public class NotificationFactory {
    public Notification createNotification(String channel)
    {
        if (channel == null || channel.isEmpty())
            return null;

        return switch (channel) {
            case "SMS" -> new SmsNotification();
            case "EMAIL" -> new EmailNotification();
            case "PUSH" -> new PushNotification();
            default -> null;
        };
    }
}