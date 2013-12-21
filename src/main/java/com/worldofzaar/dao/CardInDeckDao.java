package com.worldofzaar.dao;

import com.worldofzaar.entity.CardInDeck;
import com.worldofzaar.entity.CertainTable;
import com.worldofzaar.util.HibernateUtilMain;
import com.worldofzaar.util.UserInformation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 02.12.13
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
public class CardInDeckDao extends GenericDaoMain<CardInDeck> {
    public CardInDeckDao() {
        super(new CardInDeck());
    }

    public List<CertainTable> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List requests = (List) session.createQuery("from CardInDeck").list();
            session.close();
            return requests;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public int getActiveDeckCardCount(UserInformation userInformation) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("select count(c.cardId) from CardInDeck as c where c.deck.isActive = true and c.card.user.userId = :userId and c.deck.user.userId = :userId");
            query.setParameter("userId", userInformation.getUserId());
            query.setMaxResults(1);
            List list = query.list();
            if (list == null)
                return 0;
            int count = (Integer) list.get(0);
            tx.commit();
            session.close();
            return count;
        } catch (Exception e) {
            System.out.println("getActiveDeckCardCount(userInformation) Error = " + e.getCause());
        }
        return 0;
    }

    public List<CardInDeck> getUserActiveCards(Integer userId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from CardInDeck where deck.isActive = true and card.user.userId = :userId and deck.user.userId = :userId");
            query.setParameter("userId", userId);
            List list = query.list();
            tx.commit();
            session.close();
            return list;
        } catch (Exception e) {
            System.out.println("getUserActiveCards(userInformation) Error = " + e.getCause());
        }
        return null;
    }

}
