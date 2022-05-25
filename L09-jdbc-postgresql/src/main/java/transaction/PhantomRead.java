package transaction;

import helpers.PrepareSqlData;
import util.DBUtil;

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
        DBUtil dbutil = new DBUtil();

        try {
            //start transaction
            dbutil.getConnection().setAutoCommit(false);
            dbutil.getConnection().setTransactionIsolation(transactionIsolation);
            //thread 1 try to read data twice with delay in 10 second.
            //we are expected that data will be similar.
            readData(dbutil);
            Thread.sleep(6_000);
            readData(dbutil);
            //end transaction
            dbutil.getConnection().commit();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            dbutil.close();
        }
    }

    private void thread2Work() {
        DBUtil dbutil = new DBUtil();
        try {
            //start transaction
            dbutil.getConnection().setAutoCommit(false);
            dbutil.getConnection().setTransactionIsolation(transactionIsolation);
            //thread 2 sleep 3 second and then update data
            Thread.sleep(3_000);
            addDeleteData(dbutil);
            //end transaction
            dbutil.getConnection().commit();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            dbutil.close();;
        }
    }

    private void readData(DBUtil dbutil) throws SQLException {
        ResultSet rs = dbutil.executeQuery("SELECT SUM(score) as score FROM students WHERE id > 500");
        while(rs.next()) {
            System.out.println(rs.getInt("score"));
        }
    }

    private void addDeleteData(DBUtil dbutil) throws SQLException {
        dbutil.executeUpdate("DELETE FROM students WHERE name = 'john800'");
        dbutil.executeUpdate("INSERT INTO students(name, score) VALUES('john800', 1000)");
    }
}
