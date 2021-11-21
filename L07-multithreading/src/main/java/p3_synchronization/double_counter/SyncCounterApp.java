package p3_synchronization.double_counter;

public class SyncCounterApp {
    public static void main(String[] args) throws InterruptedException {
        SyncDoubleCounter counter = new SyncDoubleCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment1();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.decrement1();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment2();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.decrement2();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("counter1 = " + counter.getValue1());
        System.out.println("counter2 = " + counter.getValue2());
    }
}
