package exercise_1;

import config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        exercise_1();
    }

    public static void exercise_1() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        try {

            //CREATE
            Catalog createCatalog = new Catalog("catalog 1");
            session.beginTransaction();
            session.save(createCatalog);
            session.getTransaction().commit();

            //READ
            session.beginTransaction();
            Catalog readCatalog = session.get(Catalog.class, 1L);
            System.out.println(readCatalog.getTitle());
            session.getTransaction().commit();

            //UPDATE
            session.beginTransaction();
            Catalog updateCatalog = session.get(Catalog.class, 1L);
            updateCatalog.setTitle("SUPER CATALOG");
            session.getTransaction().commit();

            //DELETE
            session.beginTransaction();
            Catalog catalog = session.get(Catalog.class, 1L);
            session.delete(catalog);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sf.close();
        }
    }
}