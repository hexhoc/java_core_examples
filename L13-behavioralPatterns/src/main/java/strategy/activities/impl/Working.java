package strategy.activities.impl;

import strategy.activities.Activity;

public class Working implements Activity {
    @Override
    public void doActivity() {
        System.out.println("Working...");
    }
}
