package exercise_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConvertStringToIntegerApp {

    public static void main(String[] args) {
        convertStringListToIntegerList();
    }

    public static void convertStringListToIntegerList() {
        List<String> listOfStrings = Arrays.asList("1", "5", "A", "3", "-", "6", "4", "2");

        List<Integer> listOfIntegers = listOfStrings.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return Character.isDigit(s.charAt(0));
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        }).sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return -1;
                }
                if (o1 > o2) {
                    return 1;
                }
                return 0;
            }
        }).collect(Collectors.toList());

        System.out.println(listOfStrings.toString());
        System.out.println(listOfIntegers.toString());
    }

}

