package strategy;

import strategy.activities.impl.Eating;
import strategy.activities.impl.Reading;
import strategy.activities.impl.Sleeping;
import strategy.activities.impl.Working;

public class MainApp {
    public static void main(String[] args) {
        User user = new User();
        user.setActivity(new Working());
        user.executeActivity();

        user.setActivity(new Eating());
        user.executeActivity();

        user.setActivity(new Reading());
        user.executeActivity();

        user.setActivity(new Sleeping());
        user.executeActivity();
    }
}
