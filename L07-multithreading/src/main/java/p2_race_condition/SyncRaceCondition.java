package p2_race_condition;

public class SyncRaceCondition {

    public static void main(String[] args) {
        SyncCounter counter = new SyncCounter();

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                counter.increment();

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                counter.decrement();

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter.getValue());
    }
}
