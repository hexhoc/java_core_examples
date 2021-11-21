package p1_creation_and_base;

public class StopThreadApp {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            int i = 0;
            while(true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }

                System.out.println(i);

                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    break;
                }

                i++;
            }

            System.out.println("END SUBORDINATE THREAD");
        });

        t.start();

        Thread.sleep(5_000);

        t.interrupt();

        System.out.println("END MAIN THREAD");

    }
}
