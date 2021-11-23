package p6_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleLockApp {

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();

        new Thread(() -> {
            System.out.println("THREAD 1 START");
            lock.lock();
            try {
                System.out.println("THREAD 1 LOCK");
                Thread.sleep(10_000);
                System.out.println("THREAD 1 END");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            System.out.println("THREAD 2 START");
            try {
                if (lock.tryLock(3, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("THREAD 2 LOCK");
                        Thread.sleep(10_000);
                        System.out.println("THREAD 2 END");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }  finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println("THREAD 2 BAD");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


        }
    }
