package transaction;

import helpers.DBConnector;
import helpers.PrepareSqlData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhantomRead {

    //In postreSQL this isolation prevent repeatable read and phantom read problem
    private static int transactionIsolation = Connection.TRANSACTION_REPEATABLE_READ;

    public static void main(String[] args) {
        //Before start this example set isolation level READ COMMITTED
        PrepareSqlData.prepare();
        PhantomRead r = new PhantomRead();
        new Thread(r::thread1Work).start();
        new Thread(r::thread2Work).start();

    }

    private void thread1Work() {
        DBConnector conn = new DBConnector();
        try {
            conn.connect();
            //start transaction
            conn.connection.setAutoCommit(false);
            conn.connection.setTransactionIsolation(transactionIsolation);
            //thread 1 try to read data twice with delay in 10 second.
            //we are expected that data will be similar.
            readData(conn);
            Thread.sleep(6_000);
            readData(conn);
            //end transaction
            conn.connection.commit();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    private void thread2Work() {
        DBConnector conn = new DBConnector();
        try {
            conn.connect();
            //start transaction
            conn.connection.setAutoCommit(false);
            conn.connection.setTransactionIsolation(transactionIsolation);
            //thread 2 sleep 3 second and then update data
            Thread.sleep(3_000);
            addDeleteData(conn);
            //end transaction
            conn.connection.commit();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    private void readData(DBConnector conn) throws SQLException {
        ResultSet rs = conn.statement.executeQuery("SELECT SUM(score) as score FROM students WHERE id > 500");
        while(rs.next()) {
            System.out.println(rs.getInt("score"));
        }
    }

    private void addDeleteData(DBConnector conn) throws SQLException {
        conn.statement.execute("DELETE FROM students WHERE name = 'john800'");
        conn.statement.execute("INSERT INTO students(name, score) VALUES('john800', 1000)");
    }
}
