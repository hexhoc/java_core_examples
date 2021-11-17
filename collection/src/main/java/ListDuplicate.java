import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListDuplicate {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("1","1","2","2","3","4","5","6","7","8","9","10","10","10","10"));
        findAllDuplicate(list);
    }

    public static <T> void findAllDuplicate(ArrayList<T> list) {
        //step 1. use hashset
        HashSet<T> set = new HashSet<>();
        HashSet<T> notUnique = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            if(set.contains(list.get(i))) {
                notUnique.add(list.get(i));
            } else {
                set.add(list.get(i));
            }
        }

        System.out.println("Not unique. Use HashSet");
        System.out.println(notUnique.toString());

        //step 2. Use map
        HashMap<T, Integer> map = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), map.getOrDefault(list.get(i), 0) + 1);
        }

        System.out.println("Not unique. Use map");

        for(var entrySet:map.entrySet()) {
            if (entrySet.getValue() > 1) {
                System.out.println(entrySet.getKey());
            }
        }

        //step 3. Use sort
        HashSet<Integer> notUnique2 = new HashSet<>();
        ArrayList<Integer>list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list2.add(Integer.parseInt((String)list.get(i)));
        }
        list2.sort(Comparator.naturalOrder());

        for (int i = 0, j = 1; j < list.size(); i++, j++) {
            if (list2.get(i).equals(list2.get(j))) {
                notUnique2.add(list2.get(i));
            }
        }
        System.out.println("Not unique. Use sort");
        System.out.println(notUnique2.toString());

        //step 4. Use stream
        System.out.println("Not unique. Use stream");
        var hashMap = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for(var entrySet:hashMap.entrySet()) {
            if (entrySet.getValue() > 1) {
                System.out.println(entrySet.getKey());
            }
        }
    }

    public static void findAllUnique(ArrayList<String> list) {

    }

    public static void countWordOccurrence(ArrayList<String> list) {

    }
}
