package p3_synchronization;

public class SyncMonitorApp {

    private Object monitor = new Object();

    public static void main(String[] args) {
        SyncMonitorApp syncMonitorApp = new SyncMonitorApp();

        new Thread(() -> syncMonitorApp.method1()).start();
        new Thread(() -> syncMonitorApp.method1()).start();
        new Thread(() -> syncMonitorApp.method1()).start();
    }

    public void method1() {
        System.out.println("NON-SYNC METHOD START " + Thread.currentThread().getName());

        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("NON-SYNC METHOD END " + Thread.currentThread().getName());

        synchronized(monitor) {
            System.out.println("SYNC METHOD START " + Thread.currentThread().getName());

            for (int i = 0; i < 3; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("SYNC METHOD END " + Thread.currentThread().getName());
        }
    }
}
