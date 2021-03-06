package com.worldofzaar.dao;

import com.worldofzaar.entity.ActiveCoalition;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilActive;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:06
 * To change this template use File | Settings | File Templates.
 */
public class WarriorCardDao extends GenericDaoMain<WarriorCard> {
    public WarriorCardDao() {
        super(new WarriorCard());
    }

    public List<WarriorCard> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List warriorCards = (List) session.createQuery("from WarriorCard").list();
            session.close();
            return warriorCards;
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
            Query query = session.createQuery("delete WarriorCard where cardId = :cardId");
            query.setParameter("cardId", cardId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("remove(cardId) Error = " + e.getCause());
        }
    }

    public List<Object[]> getCompositeWarriorsCards(String lang) {
        try {

            Session session = HibernateUtilMain.getSessionFactory().openSession();
            StringBuilder query = new StringBuilder();

            List warriorCards = (List) session.createQuery(
                    "from WarriorCard as w,RuCardText as t,RuPropertyText as pt " +
                    "where w.cardId=t.warriorCard.cardId  AND w.cardId=pt.warriorCard.cardId "
                    ).list();
            session.close();
            return warriorCards;

        } catch (Exception e) {
            System.out.println("getCompositeWarriorsCards(lang) Error(DAO) = " + e.getCause());

        }
        return null;
    }
}
