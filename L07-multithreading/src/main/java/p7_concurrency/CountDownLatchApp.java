package p7_concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchApp {
    private final static int COUNT = 6;


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);

        System.out.println("MAIN THREAD START");

        for (int i = 0; i < COUNT; i++) {
            final int w = i;
            new Thread(()->{
                doSomething();
                System.out.println("THREAD " + w);
                countDownLatch.countDown();
            }).start();
        }

        try {
            countDownLatch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("MAIN THREAD END");

    }

    public static void doSomething() {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
