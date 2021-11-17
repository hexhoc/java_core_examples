import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListModification {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        while (list.remove(Integer.valueOf(0))) ;

        list.sort((o1, o2) -> {
            if (o1 % 2 > 0) return 1;
            else return -1;
        });


        for (int i = 0, count = 0; i < list.size(); i++) {
            if (count == 1) {
                list.remove(i);
                count = 0;
                i--;
            } else {
                count++;
            }
        }

        var it =list.iterator();
        while(it.hasNext()) {
            var value = it.next();
            if (value.equals(1) || value.equals(5)) {
                it.remove();
            }
        }

        System.out.println(list.toString());
    }
}
