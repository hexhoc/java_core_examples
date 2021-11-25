package exercise_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FrequencyNouns {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Map<String, Integer> nounsMap = new HashMap<>();
        List<String> bookWordsList = new ArrayList<>();

        try {
            nounsMap = Files.lines(Paths.get("L08-stream-api/src/main/resources/frequently_used_nouns.txt"))
                    .flatMap(s -> Arrays.asList(s.split("\\s")).stream())
                    .collect(Collectors.toMap(Function.identity(), s -> 0));

            bookWordsList = Files.lines(Path.of("L08-stream-api/src/main/resources/konyok_gorbunok.txt"))
                    .flatMap(s -> Arrays.stream(s.split("\\s")))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        for (String word : bookWordsList) {
            if (nounsMap.containsKey(word)) {
                nounsMap.put(word, nounsMap.get(word) + 1);
            }
        }

        System.out.println(System.currentTimeMillis() - start + " ms");

        Map<String, Integer> topUsedNouns =
                nounsMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        //OR ONLY PRINT TOP 10
        nounsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .forEach(System.out::println);
    }
}
