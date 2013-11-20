package com.worldofzaar.dao;

import com.worldofzaar.entity.EngClassText;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
public class EngClassTextDao extends GenericDaoMain<EngClassText> {
    public EngClassTextDao() {
        super(new EngClassText());
    }

    public List<EngClassText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List engClass = (List) session.createQuery("from EngClassText").list();
            session.close();
            return engClass;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public EngClassText getTextByClassId(Integer classId) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from EngClassText where classification.classificationId = :classId");
            query.setParameter("classId", classId);
            query.setMaxResults(1);
            List text = query.list();
            session.close();
            if (text == null)
                return null;
            else
                return (EngClassText) text.get(0);
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
            Query query = session.createQuery("delete EngClassText where classification.classificationId = :classId");
            query.setParameter("classId", classId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("deleteText(classId) Error = " + e.getCause());
        }
    }
}
