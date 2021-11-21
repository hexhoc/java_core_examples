package p3_synchronization;

public class SyncMethodsApp {

    public static void main(String[] args) {
        SyncMethodsApp e1 = new SyncMethodsApp();

        new Thread(() -> e1.method1()).start();
        new Thread(() -> e1.method2()).start();

    }

    public synchronized void method1() {
        System.out.println("M1-start");
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("M1-end");
    }

    public synchronized void method2() {
        System.out.println("M2-start");
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("M2-end");
    }
}
