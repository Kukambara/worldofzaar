package com.worldofzaar.dao;

import com.worldofzaar.entity.SupportCard;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

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

    public List<Object[]> getCompositeSupportCards(String lang) {
        try {

            Session session = HibernateUtilMain.getSessionFactory().openSession();

            List supportCards = (List) session.createQuery("select " +
                    "w.cardId, w.classification,t.cardName,w.set,t.cardSlogan," +
                    "pt.propertyInfo,w.cardPicture,w.isElite from SupportCard as w,RuCardText as t,RuPropertyText as pt where pt.supportCard.cardId = w.cardId AND t.supportCard.cardId=w.cardId").list();
            session.close();
            return supportCards;
        } catch (Exception e) {
            System.out.println("getCompositeSupportCards(lang) Error = " + e.getCause());
        }
        return null;
    }
}
