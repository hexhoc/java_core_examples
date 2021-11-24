package p7_concurrency;

import java.util.concurrent.Semaphore;

public class SemaphoreApp {
    private static final Semaphore semaphore = new Semaphore(4);

    public static void main(String[] args) {
        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {
                doSomething();
            }
        };

        for (int i = 0; i < 6; i++) {
            new Thread(runnableTask).start();
        }
    }


    public static void doSomething() {
        try {
            semaphore.acquire();
            System.out.println("THREAD START "+ Thread.currentThread().getName());
            Thread.sleep(5_000);
            System.out.println("THREAD END "+ Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
