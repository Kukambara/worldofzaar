package com.worldofzaar.dao;

import com.worldofzaar.entity.Set;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class SetDao extends GenericDaoMain<Set> {
    public SetDao() {
        super(new Set());
    }

    public List<Set> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List sets = (List) session.createQuery("from Set").list();
            session.close();
            return sets;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public void deleteSet(Integer setId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete Set as s where s.setId = :setId");
            query.setParameter("setId", setId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("deleteSet(setId) Error = " + e.getCause());
        }
    }
}
