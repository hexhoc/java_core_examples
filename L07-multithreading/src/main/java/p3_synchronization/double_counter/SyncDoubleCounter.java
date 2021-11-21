package p3_synchronization.double_counter;

public class SyncDoubleCounter {
    private int value1 = 0;
    private int value2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void increment1() {
        synchronized (lock1) {
            value1++;
        }
    }

    public void decrement1() {
        synchronized (lock1) {
            value1--;
        }
    }

    public void increment2() {
        synchronized (lock2) {
            value2++;
        }
    }

    public void decrement2() {
        synchronized (lock2) {
            value2--;
        }

    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }
}
