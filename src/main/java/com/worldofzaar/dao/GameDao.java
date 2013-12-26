package com.worldofzaar.dao;

import com.worldofzaar.entity.Game;
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
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
public class GameDao extends GenericDaoActive<Game> {
    public GameDao() {
        super(new Game());
    }

    public List<Game> list() {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            List games = (List) session.createQuery("from Game").list();
            session.close();
            return games;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public boolean isGameCreated(UserInformation userInformation) {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            Query query = session.createQuery("select count(g.gameId) from Game as g where g.fourthHero.userId = :id or g.thirdHero.userId = :id or g.secondHero.userId = :id or g.firstHero.userId = :id");
            query.setParameter("id", userInformation.getUserId());
            query.setMaxResults(1);
            List list = query.list();
            if (list == null)
                return false;
            int count = (Integer) list.get(0);

            session.close();
            return (count == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("isGameCreated(userInformation) Error = " + e.getCause());
        }
        return false;
    }

    public boolean isGameReady(UserInformation userInformation) {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            Query query = session.createQuery("from Game where (fourthHero.userId = :id or thirdHero.userId = :id or secondHero.userId = :id or firstHero.userId = :id) and isReady = true");
            query.setParameter("id", userInformation.getUserId());
            query.setMaxResults(1);
            List list = query.list();
            if (list == null)
                return false;
            int count = (Integer) list.get(0);
            session.close();
            return (count == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("isGameReady(userInformation) Error = " + e.getCause());
        }
        return false;
    }

    public Game getGame(Integer userId) {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            Query query = session.createQuery("from Game where fourthHero.userId = :id or thirdHero.userId = :id or secondHero.userId = :id or firstHero.userId = :id");
            query.setParameter("id", userId);
            query.setMaxResults(1);
            List list = query.list();
            if (list == null)
                return null;
            session.close();
            return (Game) list.get(0);
        } catch (Exception e) {
            System.out.println("getGame(userId) Error = " + e.getCause());
        }
        return null;
    }

    public void gameIsReady(UserInformation userInformation) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("update Game set isReady = true where  fourthHero.userId = :id or thirdHero.userId = :id or secondHero.userId = :id or firstHero.userId = :id");
            query.setParameter("id", userInformation.getUserId());
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("gameIsReady(userInformation) Error = " + e.getCause());
        }
    }

}
