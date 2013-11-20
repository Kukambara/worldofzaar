package com.worldofzaar.dao;

import com.worldofzaar.entity.RuClassText;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class RuClassTextDao extends GenericDaoMain<RuClassText> {
    public RuClassTextDao() {
        super(new RuClassText());
    }

    public List<RuClassText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List ruClass = (List) session.createQuery("from RuClassText").list();
            session.close();
            return ruClass;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public RuClassText getTextByClassId(Integer classId) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from RuClassText where classification.classificationId = :classId");
            query.setParameter("classId", classId);
            query.setMaxResults(1);
            List text = query.list();
            session.close();
            if (text == null)
                return null;
            else
                return (RuClassText) text.get(0);
        } catch (Exception e) {
            System.out.println(" getTextByClassId(classId) Error = " + e.getCause());
        }
        return null;
    }
    public void deleteText(Integer classId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete RuClassText where classification.classificationId = :classId");
            query.setParameter("classId", classId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("deleteText(classId) Error = " + e.getCause());
        }
    }

}
