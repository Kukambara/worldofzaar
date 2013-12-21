package com.worldofzaar.dao;

import com.worldofzaar.entity.ActiveCard;
import com.worldofzaar.util.HibernateUtilActive;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class ActiveCardDao extends GenericDaoActive<ActiveCard> {

    public static final int MAX_CARDS_IN_HAND = 5;

    public ActiveCardDao() {
        super(new ActiveCard());
    }

    public List<ActiveCard> list() {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            List activeDecks = (List) session.createQuery("from ActiveCard").list();
            session.close();
            return activeDecks;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public List<ActiveCard> getUserActiveCardsInHand(Integer userId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from ActiveCard where hero.userId = :id and isHand = true");
            query.setParameter("id", userId);
            query.setMaxResults(MAX_CARDS_IN_HAND);
            List list = query.list();
            tx.commit();
            session.close();
            return list;
        } catch (Exception e) {
            System.out.println("getUserActiveCardsInHand(userId) Error = " + e.getCause());
        }
        return null;

    }
}
