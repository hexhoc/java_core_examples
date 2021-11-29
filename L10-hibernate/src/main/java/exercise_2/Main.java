package exercise_2;

import config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        exercise_2();
    }

    public static void exercise_2() {
        createData();
        deleteData();
    }

    public static void createData() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        try {
            session.beginTransaction();

            //CREATE AUTHORS
            Author author1 = new Author("AUTHOR 1");
            Author author2 = new Author("AUTHOR 2");
            session.save(author1);
            session.save(author2);

            //CREATE BOOKS
            Book book1 = new Book("BOOK 1", author1);
            Book book2 = new Book("BOOK 2", author1);
            Book book3 = new Book("BOOK 3", author2);
            Book book4 = new Book("BOOK 4", author2);
            session.save(book1);
            session.save(book2);
            session.save(book3);
            session.save(book4);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteData() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        try {
            session.createNativeQuery("truncate books").executeUpdate();
            session.createNativeQuery("truncate authors").executeUpdate();
            session.createNativeQuery("truncate readers").executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
