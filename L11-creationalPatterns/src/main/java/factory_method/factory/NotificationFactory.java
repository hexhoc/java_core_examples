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
        if ("SMS".equals(channel)) {
            return new SmsNotification();
        }
        else if ("EMAIL".equals(channel)) {
            return new EmailNotification();
        }
        else if ("PUSH".equals(channel)) {
            return new PushNotification();
        }
        return null;
    }
}