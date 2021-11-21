package p8_wait_notify;

public class WaitNotifyApp {

    //we use it variable on multiply thread at once. We should make it volatile
    private volatile char currentChar = 'A';
    private Object lock = new Object();

    public static void main(String[] args) {
        WaitNotifyApp waitNotifyApp = new WaitNotifyApp();
        new Thread(()->waitNotifyApp.printA()).start();
        new Thread(()->waitNotifyApp.printB()).start();
    }

    public void printA() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar != 'A') {
                        //using wait we are stuck here and waiting other thread
                        lock.wait();
                    }
                    System.out.print(currentChar);
                    currentChar = 'B';
                    //notify all that they can no more wait and try to execute again
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar != 'B') {
                        //using wait we are stuck here and waiting other thread
                        lock.wait();
                    }
                    System.out.print(currentChar);
                    currentChar = 'A';
                    //notify all that they can no more wait and try to execute again
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
