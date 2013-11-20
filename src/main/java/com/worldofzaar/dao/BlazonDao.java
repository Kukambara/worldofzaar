package com.worldofzaar.dao;

import com.worldofzaar.entity.Blazon;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */
public class BlazonDao extends GenericDaoMain<Blazon> {
    public BlazonDao() {
        super(new Blazon());
    }

    public List<Blazon> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List blazons = (List) session.createQuery("from Blazon").list();
            session.close();
            return blazons;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public List<Blazon> getBlazonsByClothId(Integer clothId) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from Blazon where cloth.clothId = :clothId");
            query.setParameter("clothId", clothId);
            List<Blazon> blazons = query.list();
            session.close();
            return blazons;
        } catch (Exception e) {
            System.out.println("getBlazonsByClothId(clothId) Error = " + e.getCause());
        }
        return null;
    }

    public void deleteBlazon(Integer blazonId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete Blazon where blazonId = :blazonId");
            query.setParameter("blazonId", blazonId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("deleteBlazon(blazonId) Error = " + e.getCause());
        }
    }

    public Integer getClothIdByBlazonId(Integer id) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("select cloth.clothId from Blazon  where blazonId = :id");
            query.setParameter("id", id);
            query.setMaxResults(1);
            List race = query.list();
            session.close();
            return (Integer) race.get(0);
        } catch (Exception e) {
            System.out.println("getClothIdByBlazonId(id) Error = " + e.getCause());
        }
        return null;
    }

}
