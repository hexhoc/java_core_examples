package helpers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DBUtil;

public class PrepareSqlData {

    private static DBUtil dbutil = new DBUtil();

    public static void prepare() {
        try {

            //CREATE TABLE
            dbutil.executeUpdate("DROP TABLE students");
            dbutil.executeUpdate("CREATE TABLE IF NOT EXISTS students (\n" +
                    "    id serial PRIMARY KEY, \n" +
                    "    name varchar(200) NOT NULL,\n" +
                    "    score int2\n" +
                    ")");


            //INSERT DATA
            PreparedStatement ps = dbutil.getConnection().prepareStatement("INSERT INTO students(name, score) VALUES (?,?)");
            for (int i = 0; i < 1000; i++) {
                ps.setString(1, "john" + i);
                ps.setInt(2, (int)(99 * Math.random()));
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbutil.close();
        }

    }
}
