import com.google.common.collect.Lists;
import java.util.List;
import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {
        System.out.println("HELLO");
        List<String> names = Lists.newArrayList("John", "Adam", "Jane");
        List<String> reversed = Lists.reverse(names);
        System.out.println(reversed.toString());
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}
