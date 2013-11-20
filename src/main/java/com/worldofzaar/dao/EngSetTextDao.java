package com.worldofzaar.dao;

import com.worldofzaar.entity.EngSetText;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
public class EngSetTextDao extends GenericDaoMain<EngSetText> {

    public EngSetTextDao() {
        super(new EngSetText());
    }

    public List<EngSetText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List engCardText = (List) session.createQuery("from EngSetText ").list();
            session.close();
            return engCardText;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public EngSetText getTextBySetId(Integer setId) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from EngSetText as s where s.set.setId = :setId");
            query.setParameter("setId", setId);
            query.setMaxResults(1);
            List text = query.list();
            session.close();
            if (text == null)
                return null;
            else
                return (EngSetText) text.get(0);
        } catch (Exception e) {
            System.out.println("getTextBySetId(setId) Error = " + e.getCause());
        }
        return null;
    }

    public void deleteText(Integer setId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete EngSetText as s  where  s.set.setId =:setId");
            query.setParameter("setId", setId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("deleteText(Integer setId) Error = " + e.getCause());
        }
    }

}
