package facade;

public class MainApp {
    public static void main(String[] args) {

        DataSourceManager manager = new DataSourceManager();
        manager.writeDataInMemory("HELLO");
        manager.readDataFromMemory();

    }
}
