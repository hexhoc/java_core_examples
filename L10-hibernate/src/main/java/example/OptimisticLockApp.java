package example;

import config.HibernateUtil;
import example.entity.BigItem;
import exercise_1.Catalog;
import exercise_2.Author;
import exercise_2.Book;
import exercise_2.Reader;
import exercise_2.Review;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.concurrent.CountDownLatch;

//before start execute file base.sql
public class OptimisticLockApp {

    public static void main(String[] args) {

        System.out.println("HELLO");
        CountDownLatch countDownLatch = new CountDownLatch(2);

        //we are start two transaction first fast, second slow, they modify the same object
        //First transaction complete successfully, but second transaction will be rollback

        new Thread(()-> {
            updateBigItem("THREAD 1", 1_500);
            countDownLatch.countDown();
        }).start();

        new Thread(()->{
            updateBigItem("THREAD 2", 1_000);
            countDownLatch.countDown();
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void updateBigItem(String threadNum, int timeGapMs) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();

        try {
            System.out.println(threadNum+" READ");
            BigItem bigItem = session.get(BigItem.class, 1L, LockMode.OPTIMISTIC);


            System.out.println(threadNum+" UPDATE");
            int value = bigItem.getValue();
            bigItem.setValue(++value);

            uncheckableSleep(timeGapMs);

            session.save(bigItem);
            session.getTransaction().commit();
            System.out.println(threadNum + " SUCCESS");
        } catch (Exception e) {
            System.out.println(threadNum + " FAILURE");
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static void uncheckableSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
