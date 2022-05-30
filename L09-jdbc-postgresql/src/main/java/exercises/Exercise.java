package exercises;

import util.DBUtil;

import java.sql.*;


public class Exercise {

    private static DBUtil dbutil = new DBUtil();

    public static void main(String[] args) {

        try {
            cleanTable();
            insertBatchQuery();
            insertUncommittedQuery();
            selectQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void cleanTable() throws SQLException {
        dbutil.execute("truncate students");
    }

    private static void insertBatchQuery() throws SQLException {
        //disable autocommit, and then we use it on our own
        dbutil.getConnection().setAutoCommit(false);
        PreparedStatement preparedStatement = dbutil.getConnection().prepareStatement("insert into students(name, score) values(?,?)");
        for (int i = 0; i < 100; i++) {
            preparedStatement.setString(1, "john"+i);
            preparedStatement.setInt(2, (int) (99 * Math.random()));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        dbutil.getConnection().commit();
    }

    private static void insertUncommittedQuery() throws SQLException {
        //disable autocommit, and then we use it on our own
        dbutil.getConnection().setAutoCommit(false);
        PreparedStatement preparedStatement = dbutil.getConnection().prepareStatement("insert into students(name, score) values(?,?)");
        try {

            for (int i = 0; i < 100; i++) {
                preparedStatement.setString(1, "john" + i);
                preparedStatement.setInt(2, (int) (99 * Math.random()));
                preparedStatement.execute();
                if (i > 50) {
                    throw new RuntimeException();
                }
            }
            dbutil.getConnection().commit();
        } catch (Exception E) {
            dbutil.getConnection().rollback();
        }

    }

    private static void selectQuery() throws SQLException {
        String sqlQuery = "select name, score from students where score > 80 order by score desc";
        //ResultSet implement AutoCloseable, that mean it close automatically, if we use try-with-resource
        try(ResultSet rs = dbutil.executeQuery(sqlQuery)) {
            while(rs.next()) {
                String name = rs.getString("name");
                int score = rs.getInt("score");
                System.out.println(name + " " + score);
            }
        }
    }
}
