package exercise_1;

public class FillBigArrayApp {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        FillBigArrayApp f = new FillBigArrayApp();
        float[] destArr = new float[100_000_001];
        float[] arr1 = new float[25_000_000];
        float[] arr2 = new float[25_000_000];
        float[] arr3 = new float[25_000_000];
        float[] arr4 = new float[25_000_000];

        Thread t1 = new Thread(() -> f.method1(arr1));
        Thread t2 = new Thread(() -> f.method1(arr2));
        Thread t3 = new Thread(() -> f.method1(arr3));
        Thread t4 = new Thread(() -> f.method1(arr4));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.arraycopy(arr1, 0, destArr,0, arr1.length);
        System.arraycopy(arr2, 0, destArr,arr1.length, arr2.length);
        System.arraycopy(arr1, 0, destArr,arr2.length, arr3.length);
        System.arraycopy(arr2, 0, destArr,arr3.length, arr4.length);
        System.out.println(System.currentTimeMillis() - start + " ms");


    }

    public void method1 (float[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2)) + 1;
        }

    }
}
