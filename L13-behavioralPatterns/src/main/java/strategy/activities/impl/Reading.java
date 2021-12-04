package strategy.activities.impl;

import strategy.activities.Activity;

public class Reading implements Activity {
    @Override
    public void doActivity() {
        System.out.println("Reading...");
    }
}
