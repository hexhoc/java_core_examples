import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ListCapacity {
    public static void main(String[] args) throws IOException {
        String filepath = "src/main/resources/textfile.txt";

        long start = System.currentTimeMillis();

        FileReader fl = new FileReader(filepath);
        LineNumberReader lnr = new LineNumberReader(fl);

        //create arrayList, with initial capacity
        ArrayList<String> list = new ArrayList<>();
        list.ensureCapacity(10);

        try(BufferedReader br = new BufferedReader(fl)) {

            for(String line; (line = br.readLine()) != null; ) {
                list.add(line);
            }
        } finally {
            fl.close();
            lnr.close();
        }

        for (int i = 0; i < list.size() / 2; i++) {
            list.remove(0);
        }

        //trim capacity to list size
        list.trimToSize();

        System.out.println(System.currentTimeMillis() - start + " ms");

        long start2 = System.currentTimeMillis();

        //SIMPLE WAY
        Path path = Paths.get(filepath);
        var list2 = Files.readAllLines(path);

        System.out.println(System.currentTimeMillis() - start2 + " ms");

    }

}
