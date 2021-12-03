package facade.datasources.impl;

import facade.datasources.DataSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileDataSource implements DataSource {
    private String name;

    public FileDataSource() {
        this.name = "L12-structuralPatterns/src/main/resources/datafile.txt";
    }

    public FileDataSource(String name) {
        this.name = name;
    }

    @Override
    public void writeData(String data) {
        try(BufferedWriter bw = Files.newBufferedWriter(Path.of(name),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            bw.write(data);

        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String readData() {

        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = Files.newBufferedReader(Path.of(name))) {
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
