package strategy;

import strategy.activities.Activity;

public class User {
    private Activity activity;

    public User() { }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void executeActivity() {
        activity.doActivity();
    }
}
