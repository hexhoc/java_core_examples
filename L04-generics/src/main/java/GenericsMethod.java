
public class GenericsMethod {
    public static void main(String[] args) {
        GenericsMethod gm = new GenericsMethod();
        gm.print(1, "text");
        gm.print(2.0, 5.5f);
    }

    //This method will work like a dynamic typing
    public <K, V> void print(K key, V value) {
        System.out.println("key: "+ key + " " + "value: "+ value);
    }
}
