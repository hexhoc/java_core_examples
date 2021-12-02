package singleton;

public class MainApp {
    public static void main(String[] args) {
        DBConnector dbConnector1 = DBConnector.getConnection("jdbc:postgresql://localhost:5432/postgres");
        DBConnector dbConnector2 = DBConnector.getConnection("Привет");
        System.out.println(dbConnector1.getConnectionString());
        System.out.println(dbConnector2.getConnectionString());
    }
}
