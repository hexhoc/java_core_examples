package example.entity;

import config.HibernateUtil;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.concurrent.CountDownLatch;

//before start execute file base.sql
public class PesimisticLock {

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        //we are start two transaction first fast, second slow, they modify the same object
        //First transaction complete successfully, but second transaction will be rollback

        new Thread(()-> {
            Session session = sf.openSession();

        }).start();

        new Thread(()->{
            Session session = sf.openSession();
            try {
                session.beginTransaction();
                System.out.println("THREAD 2 START");
                BigItem bigItem = session.get(BigItem.class, 1L, LockMode.PESSIMISTIC_WRITE);
                System.out.println("THREAD 2 UPDATE");
                uncheckableSleep(10_000);
                session.save(bigItem);

                session.getTransaction().commit();
                System.out.println("THREAD 2 SUCCESSFUL");

            } catch (Exception e) {
                System.out.println("THREAD 2 FAILURE");
                e.printStackTrace();
                session.getTransaction().rollback();
            } finally {
                session.close();
                countDownLatch.countDown();
            }
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sf.close();
    }

    public static void uncheckableSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
