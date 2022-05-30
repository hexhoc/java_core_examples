package helpers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DBUtil;

public class PrepareSqlData {

    private static DBUtil dbutil = new DBUtil();

    public static void prepare() {
        try {

            //CREATE TABLE
            dbutil.execute("DROP TABLE students");
            dbutil.execute("""
                    CREATE TABLE IF NOT EXISTS students (
                        id serial PRIMARY KEY,
                        name varchar(200) NOT NULL,
                        score int2
                    )
                    """);

            dbutil.execute("""
                    CREATE TABLE IF NOT EXISTS students (
                        id serial PRIMARY KEY,
                        name varchar(200) NOT NULL,
                        score int2
                    )
                    """);

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
