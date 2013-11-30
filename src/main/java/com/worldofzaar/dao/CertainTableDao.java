package com.worldofzaar.dao;

import com.worldofzaar.entity.CertainTable;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
public class CertainTableDao extends GenericDaoMain<CertainTable> {
    public CertainTableDao() {
        super(new CertainTable());
    }

    public List<CertainTable> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List requests = (List) session.createQuery("from CertainTable").list();
            session.close();
            return requests;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public List<CertainTable> getCertainTables(int size, int cost) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from CertainTable where tableSize = :size and tableCost = :cost");
            query.setParameter("size", size);
            query.setParameter("cost", cost);
            List tables = query.list();
            session.close();
            return tables;
        } catch (Exception e) {
            System.out.println("getCertainTables(size, cost) Error = " + e.getCause());
        }
        return null;
    }
}
