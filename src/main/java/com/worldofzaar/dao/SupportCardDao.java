package com.worldofzaar.dao;

import com.worldofzaar.entity.SupportCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public class SupportCardDao extends GenericDaoMain<SupportCard> {
    public SupportCardDao() {
        super(new SupportCard());
    }

    public List<SupportCard> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List supportCards = (List) session.createQuery("from SupportCard").list();
            session.close();
            return supportCards;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public void remove(Integer cardId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete SupportCard where cardId = :cardId");
            query.setParameter("cardId", cardId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("remove(cardId) Error = " + e.getCause());
        }
    }

    public List<Object[]> getCompositeSupportCards(String lang) {
        try {

            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List supportCards = (List) session.createQuery(
                    "from SupportCard as s,RuCardText as t, RuPropertyText as p " +
                    "where s.cardId=t.supportCard.cardId AND s.cardId=p.supportCard.cardId").list();
            session.close();
            return supportCards;
        } catch (Exception e) {
            System.out.println("getCompositeSupportCards(lang) Error(DAO) = " + e.getCause());
        }
        return null;
    }
}
