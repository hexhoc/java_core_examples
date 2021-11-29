package config;

import exercise_1.Catalog;
import exercise_2.Author;
import exercise_2.Book;
import exercise_2.Reader;
import exercise_2.Review;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .addAnnotatedClass(Catalog.class)
                    .addAnnotatedClass(Author.class)
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Reader.class)
                    .addAnnotatedClass(Review.class)
                    .buildSessionFactory();
            //CUSTOM CONFIGURATION FILE
            //return new Configuration().configure("hibernate-dev.cfg.xml").buildSessionFactory();

            //CONFIGURATION WITH ANNOTATED CLASS
//            return new Configuration()
//                    .addAnnotatedClass(Student.class)
//                    .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("build SessionFactory failed :" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close() {
        // Close all cached and active connection pools
        getSessionFactory().close();
    }

}