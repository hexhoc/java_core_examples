public class TwoGen <K,V>{
    private K key;
    private V value;

    public static void main(String[] args) {
        TwoGen<String, String> tg1 = new TwoGen<>("one", "two");
        System.out.println(tg1.getKey());
        System.out.println(tg1.getValue());
    }

    public TwoGen(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
