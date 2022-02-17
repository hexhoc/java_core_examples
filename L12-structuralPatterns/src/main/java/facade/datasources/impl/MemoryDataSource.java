package facade.datasources.impl;

import facade.datasources.DataSource;

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
