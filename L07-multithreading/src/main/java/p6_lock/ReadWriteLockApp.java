package p6_lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockApp {
    public static void main(String[] args) {
        final ReadWriteLock lock = new ReentrantReadWriteLock();

        new Thread(()-> {
            var readLock = lock.readLock();
            readLock.lock();
            System.out.println("THREAD 1 READ");
            ReadWriteLockApp.sleepMethod(10_000);
            System.out.println("THREAD 1 END");
            readLock.unlock();
        }).start();

        new Thread(()-> {
            var readLock = lock.readLock();
            readLock.lock();
            System.out.println("THREAD 2 READ");
            ReadWriteLockApp.sleepMethod(10_000);
            System.out.println("THREAD 2 END");
            readLock.unlock();
        }).start();

        new Thread(()-> {
            var readLock = lock.readLock();
            readLock.lock();
            System.out.println("THREAD 3 READ");
            ReadWriteLockApp.sleepMethod(10_000);
            System.out.println("THREAD 3 END");
            readLock.unlock();
        }).start();

        new Thread(()-> {
            var writeLock = lock.writeLock();
            writeLock.lock();
            System.out.println("THREAD 4 WRITE");
            ReadWriteLockApp.sleepMethod(10_000);
            System.out.println("THREAD 4 END");
            writeLock.unlock();
        }).start();


    }

    public static void sleepMethod(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
