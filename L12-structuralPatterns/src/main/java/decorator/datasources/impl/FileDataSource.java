package decorator.datasources.impl;

import decorator.datasources.DataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class FileDataSource implements DataSource {
//    BASE DECORATOR
    private String name;

    public FileDataSource() {
        this.name = "L12-structuralPatterns/src/main/resources/datafile.txt";
    }

    public FileDataSource(String name) {
        this.name = name;
    }

    @Override
    public void writeData(String data) {

        try {
            Files.write(Path.of(name), Arrays.asList(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readData() {
        StringBuilder sb = new StringBuilder();

        try {
            List<String> fileLines = Files.readAllLines(Path.of(name));
            fileLines.forEach(sb::append);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
