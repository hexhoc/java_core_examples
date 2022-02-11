package facade;

import facade.datasources.DataSource;
import facade.datasources.impl.FileDataSource;
import facade.datasources.impl.MemoryDataSource;

public class DataSourceManager {
    public void writeDataInMemory(String data) {
        DataSource dataSource = new MemoryDataSource();
        dataSource.writeData(data);
    }

    public void readDataFromMemory() {
        DataSource dataSource = new MemoryDataSource();
        String data = dataSource.readData();
        System.out.println(data);
    }

    public void writeDataInFile(String data) {
        DataSource dataSource = new FileDataSource();
        dataSource.writeData(data);
    }

    public void readDataFromFile() {
        DataSource dataSource = new FileDataSource();
        String data = dataSource.readData();
        System.out.println(data);
    }
}
