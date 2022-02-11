package facade.datasources.impl;

import facade.datasources.DataSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class MemoryDataSource implements DataSource {
    private String name;
    private static String data;

    public MemoryDataSource() {
        this.name = "";
    }

    @Override
    public void writeData(String data) {
        MemoryDataSource.data = data;
    }

    @Override
    public String readData() {
        return data;
    }
}
