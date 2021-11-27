package transaction;

import helpers.DBConnector;
import helpers.PrepareSqlData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepeatableRead {

    //Prevent dirty read
    private static int transactionIsolation = Connection.TRANSACTION_READ_COMMITTED;

    public static void main(String[] args) {
        //Before start this example set isolation level READ COMMITTED
        PrepareSqlData.prepare();
        RepeatableRead r = new RepeatableRead();
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
            Thread.sleep(10_000);
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
            //thread 2 sleep 5 second and then update data
            Thread.sleep(5_000);
            updateData(conn);
            //end transaction
            conn.connection.commit();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    private void readData(DBConnector conn) throws SQLException {
        ResultSet rs = conn.statement.executeQuery("SELECT name, score FROM students WHERE name='john0'");
        while(rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getInt("score"));
        }
    }

    private void updateData(DBConnector conn) throws SQLException {
        conn.statement.execute("UPDATE students SET score = 99 WHERE name = 'john0'");
    }

}

