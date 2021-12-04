package strategy.activities.impl;

import strategy.activities.Activity;

public class Eating implements Activity {

    @Override
    public void doActivity() {
        System.out.println("Eating...");
    }
}
