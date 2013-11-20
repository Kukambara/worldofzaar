package com.worldofzaar.dao;

import com.worldofzaar.entity.Subset;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 14.11.13
 * Time: 12:22
 * To change this template use File | Settings | File Templates.
 */
public class SubsetDao extends GenericDaoMain<Subset> {
    public SubsetDao() {
        super(new Subset());
    }

    public List<Subset> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List sets = (List) session.createQuery("from Subset").list();
            session.close();
            return sets;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public void deleteSubset(Integer subsetId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete Subset where subsetId = :subsetId");
            query.setParameter("subsetId", subsetId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("deleteSubset(subsetId) Error = " + e.getCause());
        }
    }
}
