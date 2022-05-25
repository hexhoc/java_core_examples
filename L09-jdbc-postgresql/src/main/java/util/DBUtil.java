package util;

import contants.AppConstants;

import java.io.Closeable;
import java.sql.*;

public class DBUtil implements Closeable {

    // private static DBUtil db;
    private Connection connection = null;
    private PreparedStatement ps;// jdbc statement

    public DBUtil() {
        // Tool classes generally use the singleton pattern
    }

    // // get static instance
    // public static synchronized DBUtil getDBUtil() {
    //     if (db == null) {
    //         db = new DBUtil();
    //     }
    //     return db;
    // }

    // connect database operation
    public synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // If not connected to the database, reconnect to the database
                Class.forName(AppConstants.JDBC_DRIVER);// classloader loads the corresponding driver
                connection = DriverManager.getConnection(
                        AppConstants.JDBC_URL,
                        AppConstants.JDBC_USERNAME,
                        AppConstants.JDBC_PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver is not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // close the database
    public void close() {
        try {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Execute the sql statement (Insert，delete）
    public int executeUpdate(String sql) {
        int result = -1;
        if (getConnection() == null) {
            return result;
        }
        try {
            ps = connection.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int executeUpdate(String sql, Object[] obj) {
        int result = -1;
        if (getConnection() == null) {
            return result;
        }
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            result = ps.executeUpdate();

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Execute query statement
    public ResultSet executeQuery(String sql) {
        if (getConnection() == null) {
            return null;
        }

        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(sql);// Create preparestatment object
            rs = ps.executeQuery();// Execute the result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet executeQuery(String sql, Object[] obj) {
        if (getConnection() == null) {
            return null;
        }

        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(sql);// create statement object
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();// Execute the result set
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
}