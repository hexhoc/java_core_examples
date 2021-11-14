import java.util.HashMap;

public class MapModification {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        testUniqueMap();
        testNotUniqueMap();
    }

    public static void testUniqueMap() throws NoSuchFieldException, IllegalAccessException {
        //TEST UNIQUE KEY
        HashMap<Integer,Integer> map = new HashMap(8);

        for (int i = 0; i < 20; i++) {
            map.put(i,i);
        }

        var tableField = HashMap.class.getDeclaredField("table");
        tableField.setAccessible(true);
        int capacity = ((Object[])tableField.get(map)).length;

        System.out.println(capacity);
    }

    public static void testNotUniqueMap() throws NoSuchFieldException, IllegalAccessException {
        //TEST NOT UNIQUE KEY
        HashMap<Cat, Integer> map = new HashMap<>(8);
        for (int i = 0; i < 20; i++) {
            map.put(new Cat(),i);
        }

        var tableField = HashMap.class.getDeclaredField("table");
        tableField.setAccessible(true);
        int capacity = ((Object[])tableField.get(map)).length;

        System.out.println(capacity);
    }

    public static void sayCapacity() {

    }

    static class Cat{
        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}
