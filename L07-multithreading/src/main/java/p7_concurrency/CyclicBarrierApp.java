package p7_concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierApp {

    private static final int COUNT = 4;
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(COUNT);

    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {
            new Thread(()->{
                doSomething();
            }).start();
        }
    }

    public static void doSomething() {
        try {
            System.out.println("THREAD " + Thread.currentThread().getName() + " PREPARE");
            Thread.sleep((long) (15_000 * Math.random()));
            System.out.println("THREAD " + Thread.currentThread().getName() + " READY");
            cyclicBarrier.await(30, TimeUnit.SECONDS);
            System.out.println("THREAD " + Thread.currentThread().getName() + " START");
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
