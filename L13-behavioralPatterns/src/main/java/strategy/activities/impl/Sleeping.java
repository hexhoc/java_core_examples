package strategy.activities.impl;

import strategy.activities.Activity;

public class Sleeping implements Activity {
    @Override
    public void doActivity() {
        System.out.println("Sleeping...");
    }
}
