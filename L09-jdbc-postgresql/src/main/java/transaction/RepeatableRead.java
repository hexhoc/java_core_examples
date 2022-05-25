package transaction;

import helpers.PrepareSqlData;
import util.DBUtil;

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
        DBUtil dbutil = new DBUtil();
        try {
            //start transaction
            dbutil.getConnection().setAutoCommit(false);
            dbutil.getConnection().setTransactionIsolation(transactionIsolation);
            //thread 1 try to read data twice with delay in 10 second.
            //we are expected that data will be similar.
            readData(dbutil);
            Thread.sleep(10_000);
            readData(dbutil);
            //end transaction
            dbutil.getConnection().commit();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            dbutil.close();;
        }
    }

    private void thread2Work() {
        DBUtil dbutil = new DBUtil();
        try {
            //start transaction
            dbutil.getConnection().setAutoCommit(false);
            dbutil.getConnection().setTransactionIsolation(transactionIsolation);
            //thread 2 sleep 5 second and then update data
            Thread.sleep(5_000);
            updateData(dbutil);
            //end transaction
            dbutil.getConnection().commit();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            dbutil.close();
        }
    }

    private void readData(DBUtil dbUtil) throws SQLException {
        ResultSet rs = dbUtil.executeQuery("SELECT name, score FROM students WHERE name='john0'");
        while(rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getInt("score"));
        }
    }

    private void updateData(DBUtil dbUtil) throws SQLException {
        dbUtil.executeQuery("UPDATE students SET score = 99 WHERE name = 'john0'");
    }

}

