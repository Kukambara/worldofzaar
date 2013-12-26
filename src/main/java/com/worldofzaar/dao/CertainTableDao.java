package com.worldofzaar.dao;

import com.worldofzaar.entity.CertainTable;
import com.worldofzaar.util.HibernateUtilMain;
import com.worldofzaar.util.UserInformation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public boolean deleteCertainTable(UserInformation userInformation) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete CertainTable where user.userId = :userId");
            query.setParameter("userId", userInformation.getUserId());
            int count = query.executeUpdate();
            tx.commit();
            session.close();
            return (count == 0) ? false : true;
        } catch (Exception e) {
            System.out.println("deleteCertainTable(userInformation) Error = " + e.getCause());
        }
        return false;
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

    public List<CertainTable> getCertainTables(int size, int cost, int minLevel, int maxLevel) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from CertainTable where tableSize = :size and tableCost = :cost and level between :minLevel and :maxLevel");
            query.setParameter("size", size);
            query.setParameter("cost", cost);
            query.setParameter("minLevel", minLevel);
            query.setParameter("maxLevel", maxLevel);
            List tables = query.list();
            session.close();
            return tables;
        } catch (Exception e) {
            System.out.println("getCertainTables(size, cost) Error = " + e.getCause());
        }
        return null;
    }

    public List<CertainTable> getCertainTables(int cost, int minLevel, int maxLevel) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from CertainTable where tableCost = :cost and level between :minLevel and :maxLevel");
            query.setParameter("cost", cost);
            query.setParameter("minLevel", minLevel);
            query.setParameter("maxLevel", maxLevel);
            List tables = query.list();
            session.close();
            return tables;
        } catch (Exception e) {
            System.out.println("getCertainTables(size, cost) Error = " + e.getCause());
        }
        return null;
    }

    public boolean isAllreadyGotIn(UserInformation userInformation) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from CertainTable where user.userId = :userId");
            query.setParameter("userId", userInformation.getUserId());
            List tables = query.list();
            session.close();
            if (tables == null)
                return false;
            if (tables.size() != 0)
                return true;
        } catch (Exception e) {
            System.out.println("getCertainTables(size, cost) Error = " + e.getCause());
        }
        return false;
    }
}
