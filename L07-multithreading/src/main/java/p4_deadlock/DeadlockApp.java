package p4_deadlock;

/**
 * Чтобы обнаружить возникший deadlock необходимо получить ThreadDump
 * Например утилита jstack где по id процесса можно вытащить инфу о тредах
 */
public class DeadlockApp {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public static void main(String[] args) {
        DeadlockApp d = new DeadlockApp();

        new Thread(() -> d.method1()).start();
        new Thread(() -> d.method2()).start();

    }

    public void method1() {

        synchronized (lock1) {
            try {
                System.out.println(Thread.currentThread().getName() + " LOCK 1");
                Thread.sleep(2_000);
                System.out.println(Thread.currentThread().getName() + " WAITING TO LOCK 2");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " LOCK 2");
                    Thread.sleep(2_000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            try {
                System.out.println(Thread.currentThread().getName() + " LOCK 2");
                Thread.sleep(2_000);
                System.out.println(Thread.currentThread().getName() + " WAITING TO LOCK 1");
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " LOCK 1");
                    Thread.sleep(2_000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
