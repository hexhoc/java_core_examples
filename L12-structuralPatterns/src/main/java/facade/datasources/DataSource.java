package facade.datasources;

public interface DataSource {
    void writeData(String data);
    String readData();
}
