package p5_executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunnableExecutorServiceApp {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(4, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        for (int i = 0; i < 8; i++) {
            es.execute(RunnableExecutorServiceApp::doSomething);
        }

        es.shutdown();
        try {
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("END");
    }

    public static void doSomething() {
        System.out.println("Start " + Thread.currentThread().getName());
        try {
            Thread.sleep(4_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End " + Thread.currentThread().getName());

    }

}
