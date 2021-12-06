package nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesDemo {

    public static final String FILEPATH1 = "L15-io-serialization/src/main/resources/textfile.txt";
    public static final String FILEPATH2 = "L15-io-serialization/src/main/resources/copy_textfile.txt";

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(FILEPATH1);

        if (Files.exists(path)) {
            //READ FILE
            Files.readAllLines(path).forEach(System.out::println);

            //WRITE FILE
            Files.write(path, Files.readAllLines(path));
        }

        Path srcPath = Paths.get(FILEPATH1);
        Path dstPath = Paths.get(FILEPATH2);

        //COPY FILE
        Files.copy(srcPath, dstPath);

    }

}
