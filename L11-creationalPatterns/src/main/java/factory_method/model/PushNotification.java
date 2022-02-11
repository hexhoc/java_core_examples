package factory_method.model;

public class PushNotification implements Notification{
    @Override
    public void notifyUser() {
        System.out.println("Sending an push notification");
    }
}
