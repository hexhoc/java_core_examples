package helpers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrepareSqlData {
    public static void prepare() {
        DBConnector conn = new DBConnector();
        try {
            conn.connect();
            //CREATE TABLE
            conn.statement.execute("DROP TABLE students");
            conn.statement.execute("CREATE TABLE IF NOT EXISTS students (\n" +
                    "    id serial PRIMARY KEY, \n" +
                    "    name varchar(200) NOT NULL,\n" +
                    "    score int2\n" +
                    ")");


            //INSERT DATA
            PreparedStatement ps = conn.connection.prepareStatement("INSERT INTO students(name, score) VALUES (?,?)");
            for (int i = 0; i < 1000; i++) {
                ps.setString(1, "john" + i);
                ps.setInt(2, (int)(99 * Math.random()));
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

    }
}
