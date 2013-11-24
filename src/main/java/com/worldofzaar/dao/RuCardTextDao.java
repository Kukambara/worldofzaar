package com.worldofzaar.dao;

import com.worldofzaar.entity.RuCardText;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class RuCardTextDao extends GenericDaoMain<RuCardText> {
    public RuCardTextDao() {
        super(new RuCardText());
    }

    public List<RuCardText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List ruCardTexts = (List) session.createQuery("from RuCardText").list();
            session.close();
            return ruCardTexts;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public void removeTextByCardId(Integer cardId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete RuCardText where warriorCard.cardId = :cardId or supportCard.cardId = :cardId");
            query.setParameter("cardId", cardId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("removeTextByCardId(cardId) Error = " + e.getCause());
        }
    }


    public RuCardText getTextByCardId(Integer cardId) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from RuCardText where supportCard.cardId = :cardId or warriorCard.cardId = :cardId");
            query.setParameter("cardId", cardId);
            query.setMaxResults(1);
            List text = query.list();
            session.close();
            if (text == null)
                return null;
            else
                return (RuCardText) text.get(0);
        } catch (Exception e) {
            System.out.println(" getTextByCardId(cardId) Error = " + e.getCause());
        }
        return null;
    }
}
