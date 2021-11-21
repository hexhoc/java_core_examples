package p3_synchronization;

public class SyncProblemApp {
    private static Integer counter = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (counter) {
                System.out.println("Thread 1 start");
                counter++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1 end");
            }
        }).start();

        new Thread(() -> {
            synchronized (counter) {
                System.out.println("Thread 2 start");
                counter++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2 end");
            }
        }).start();

        new Thread(() -> {
            synchronized (counter) {
                System.out.println("Thread 3 start");
                counter++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 3 end");
            }
        }).start();

    }

}
