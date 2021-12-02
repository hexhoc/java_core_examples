package singleton;

import java.util.Objects;

public final class DBConnector {
    private static volatile  DBConnector connection;
    private String connectionString;

    private DBConnector(String connectionString) {
        this.connectionString = connectionString;
    }

    public static synchronized DBConnector getConnection(String value) {
        if (Objects.isNull(connection)) {
            connection = new DBConnector(value);
        }

        return connection;
    }

    public String getConnectionString() {
        return connectionString;
    }
}
