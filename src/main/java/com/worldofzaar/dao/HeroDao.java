package com.worldofzaar.dao;

import com.worldofzaar.entity.Hero;
import com.worldofzaar.util.HibernateUtilActive;
import com.worldofzaar.util.UserInformation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public class HeroDao extends GenericDaoActive<Hero> {

    public HeroDao() {
        super(new Hero());
    }

    public List<Hero> list() {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            List heroes = (List) session.createQuery("from Hero ").list();
            session.close();
            return heroes;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public Hero getHero(UserInformation userInformation) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from Hero where userId = :id");
            query.setParameter("id", userInformation.getUserId());
            query.setMaxResults(1);
            List list = query.list();
            if (list == null)
                return null;
            tx.commit();
            session.close();
            return (Hero) list.get(0);
        } catch (Exception e) {
            System.out.println("getHero(userInformation) Error = " + e.getCause());
        }
        return null;
    }

    public void heroIsReady(UserInformation userInformation) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("update Hero set isReady = true where  userId = :userId");
            query.setParameter("userId", userInformation.getUserId());
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("heroIsReady(userInformation) Error = " + e.getCause());
        }
    }

    public void heroIsUnActive(Hero hero) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("update Hero set isActive = true where heroId = :heroId");
            query.setParameter("heroId", hero.getHeroId());
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("heroIsUnActive(hero) Error = " + e.getCause());
        }
    }


    public void heroIsActive(Hero hero) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("update Hero set isActive = true where heroId = :heroId");
            query.setParameter("heroId", hero.getHeroId());
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("heroIsActive(hero) Error = " + e.getCause());
        }
    }
}
