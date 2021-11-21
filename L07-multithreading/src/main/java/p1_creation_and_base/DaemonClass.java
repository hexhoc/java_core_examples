package p1_creation_and_base;

public class DaemonClass {

    public static void main(String[] args) {

        System.out.println("START");

        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.setDaemon(true);
        t.start();

        System.out.println("END");
    }

}
