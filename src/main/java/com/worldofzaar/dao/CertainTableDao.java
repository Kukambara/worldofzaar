package com.worldofzaar.dao;

import com.worldofzaar.entity.ApiTable;
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

    public boolean deleteCertainTable(ApiTable table, UserInformation userInformation) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete CertainTable where tableSize = :tableSize and tableCost = :tableCost and seatPosition = :seatPosition and user.userId = :userId");
            query.setParameter("tableSize", table.getSize());
            query.setParameter("tableCost", table.getCost());
            query.setParameter("seatPosition", table.getPosition());
            query.setParameter("userId", userInformation.getUserId());
            int count = query.executeUpdate();
            tx.commit();
            session.close();
            return (count == 0) ? false : true;
        } catch (Exception e) {
            System.out.println("deleteCertainTable(table,userInformation) Error = " + e.getCause());
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
