package p2_race_condition.model;

public class Counter {
    private int value;

    public void increment() {
        value++;
    }

    public void decrement() {
        value--;
    }

    public int getValue() {
        return value;
    }
}
