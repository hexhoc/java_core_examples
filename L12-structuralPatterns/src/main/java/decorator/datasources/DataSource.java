package decorator.datasources;

public interface DataSource {
    void writeData(String data);
    String readData();
}
