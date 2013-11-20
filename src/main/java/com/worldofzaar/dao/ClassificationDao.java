package com.worldofzaar.dao;

import com.worldofzaar.entity.Classification;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:17
 * To change this template use File | Settings | File Templates.
 */
public class ClassificationDao extends GenericDaoMain<Classification> {

    public ClassificationDao() {
        super(new Classification());
    }

    public List<Classification> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List classifications = (List) session.createQuery("from Classification ").list();
            session.close();
            return classifications;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public void deleteClass(Integer classId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete Classification where classificationId = :classId");
            query.setParameter("classId", classId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("deleteClass(classId) Error = " + e.getCause());
        }
    }

}
