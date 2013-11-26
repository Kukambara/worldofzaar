package com.worldofzaar.dao;

import com.worldofzaar.entity.MasterOfDeck;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public class MasterOfDeckDao extends GenericDaoMain<MasterOfDeck> {

    public MasterOfDeckDao() {
        super(new MasterOfDeck());
    }

    public List<MasterOfDeck> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List masterOfDecks = (List) session.createQuery("from MasterOfDeck ").list();
            session.close();
            return masterOfDecks;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public void remove(Integer masterOfDeckId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete MasterOfDeck where mastersCardId = :masterOfDeckId");
            query.setParameter("masterOfDeckId", masterOfDeckId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("remove(masterOfDeckId) Error = " + e.getCause());
        }
    }

    public MasterOfDeck findByCardId(Integer cardId) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from MasterOfDeck where supportCard.cardId = :cardId or warriorCard.cardId = :cardId");
            query.setParameter("cardId", cardId);
            query.setMaxResults(1);
            List masters = query.list();
            session.close();
            if (masters == null)
                return null;
            else
                return (MasterOfDeck) masters.get(0);
        } catch (Exception e) {
            System.out.println("findByCardId(cardId) Error = " + e.getCause());
        }
        return null;
    }
}
