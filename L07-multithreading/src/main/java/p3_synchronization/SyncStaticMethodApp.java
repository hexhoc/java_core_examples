package p3_synchronization;

public class SyncStaticMethodApp {

    public static void main(String[] args) {
        new Thread(() -> SyncStaticMethodApp.method1()).start();
        new Thread(() -> SyncStaticMethodApp.method2()).start();
    }

    public static synchronized void method1() {
        for (int i = 0; i < 3; i++) {
            System.out.println("METHOD 1");
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void method2() {
        for (int i = 0; i < 3; i++) {
            System.out.println("METHOD 2");
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
