package exercise_2;

import config.HibernateUtil;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        // before start, execute script in base.sql
        exercise_2();
    }

    public static void exercise_2() {
        readData();
    }

    private static void readData() {
        SessionFactory sf = HibernateUtil.getSessionFactory();

        try (Session session = sf.openSession()) {
            var books = session.createNativeQuery("SELECT * FROM books", Book.class).getResultList();
            for (int i = 0; i < books.size(); i++) {
                Book book = (Book) books.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
