package com.worldofzaar.dao;

import com.worldofzaar.entity.RuCardText;
import com.worldofzaar.entity.RuPropertyText;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
public class RuPropertyTextDao extends GenericDaoMain<RuPropertyText> {
    public RuPropertyTextDao(){
        super(new RuPropertyText());
    }

    public List<RuPropertyText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List ruProperty = (List) session.createQuery("from RuPropertyText").list();
            session.close();
            return ruProperty;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public RuPropertyText getTextByCardId(Integer cardId) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from RuPropertyText where supportCard.cardId = :cardId or warriorCard.cardId = :cardId");
            query.setParameter("cardId", cardId);
            query.setMaxResults(1);
            List text = query.list();
            session.close();
            if (text == null)
                return null;
            else
                return (RuPropertyText) text.get(0);
        } catch (Exception e) {
            System.out.println(" getTextByCardId(cardId) Error = " + e.getCause());
        }
        return null;
    }

    public void removeTextByCardId(Integer cardId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete RuPropertyText where warriorCard.cardId = :cardId or supportCard.cardId = :cardId");
            query.setParameter("cardId", cardId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("removeTextByCardId(cardId) Error = " + e.getCause());
        }
    }
}
