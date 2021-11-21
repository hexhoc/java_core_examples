package p1_creation_and_base;

public class ThreadExampleClass extends Thread {

    public static void main(String[] args) {
        System.out.println("START");

        ThreadExampleClass t = new ThreadExampleClass();
        t.start();

        System.out.println("END");
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
