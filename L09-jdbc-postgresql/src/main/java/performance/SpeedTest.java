package performance;

import util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;

public class SpeedTest {

    public static void main(String[] args) {
        try (DBUtil dbUtil = new DBUtil()) {

            createTables(dbUtil);
            long start = System.currentTimeMillis();

            veryFastMethod(dbUtil);

            System.out.println(System.currentTimeMillis() - start + " ms");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTables(DBUtil dbUtil) {
        dbUtil.execute("drop table price_list");
        dbUtil.execute("drop table products");

        dbUtil.execute("""
                create table products
                (
                    id   serial primary key,
                    name varchar(255) not null
                )
                """);

        dbUtil.execute("""
                create table price_list
                (
                    period     timestamp default CURRENT_TIMESTAMP not null,
                    product_id integer not null references products ON delete SET NULL,
                    price      double precision,
                    primary key (period, product_id)
                )
                """);
    }

    // 300 seconds
    public static void verySlowMethod(DBUtil dbUtil) throws SQLException {
        Connection connection = dbUtil.getConnection();
        dbUtil.execute("truncate price_list cascade");
        dbUtil.execute("truncate products");

        for (int i = 0; i < 10_000; i++) {
            PreparedStatement ps1 = connection.prepareStatement("insert into products (name) values (CONCAT('product ', ?))", Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, String.valueOf(i));
            ps1.execute();
            ResultSet keys = ps1.getGeneratedKeys();
            int productId = 0;
            if (keys.next()) {
                productId = keys.getInt(1);
            }
            for (int j = 0; j < 10; j++) {
                PreparedStatement ps2 = connection.prepareStatement("insert into price_list(period, product_id, price) values (?,?,?)");
                ps2.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now().minusDays(j)));
                ps2.setInt(2, productId);
                ps2.setDouble(3, 1000 * (j + 1));
                ps2.execute();
            }
        }
    }

    // 152 seconds
    public static void fastMethod(DBUtil dbUtil) throws SQLException {
        Connection connection = dbUtil.getConnection();
        dbUtil.execute("truncate price_list cascade");
        dbUtil.execute("truncate products");

        connection.setAutoCommit(false);

        for (int i = 0; i < 10_000; i++) {
            PreparedStatement ps1 = connection.prepareStatement("insert into products (name) values (CONCAT('product ', ?))", Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, String.valueOf(i));
            ps1.execute();
            ResultSet keys = ps1.getGeneratedKeys();
            int productId = 0;
            if (keys.next()) {
                productId = keys.getInt(1);
            }
            for (int j = 0; j < 10; j++) {
                PreparedStatement ps2 = connection.prepareStatement("insert into price_list(period, product_id, price) values (?,?,?)");
                ps2.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now().minusDays(j)));
                ps2.setInt(2, productId);
                ps2.setDouble(3, 1000 * (j + 1));
                ps2.execute();
            }
        }

        connection.commit();
    }

    // 46 seconds
    public static void veryFastMethod(DBUtil dbUtil) throws SQLException {
        Connection connection = dbUtil.getConnection();
        dbUtil.execute("truncate price_list cascade");
        dbUtil.execute("truncate products");

        connection.setAutoCommit(false);

        for (int i = 0; i < 10_000; i++) {
            PreparedStatement ps1 = connection.prepareStatement("insert into products (name) values (CONCAT('product ', ?))", Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, String.valueOf(i));
            ps1.execute();
            ResultSet keys = ps1.getGeneratedKeys();
            int productId = 0;
            if (keys.next()) {
                productId = keys.getInt(1);
            }

            PreparedStatement ps2 = connection.prepareStatement("insert into price_list(period, product_id, price) values (?,?,?)");
            for (int j = 0; j < 10; j++) {
                ps2.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now().minusDays(j)));
                ps2.setInt(2, productId);
                ps2.setDouble(3, 1000 * (j + 1));
                ps2.addBatch();
            }
            ps2.executeBatch();
        }

        connection.commit();
    }

}




