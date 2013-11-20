package com.worldofzaar.dao;

import com.worldofzaar.entity.Race;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public class RaceDao extends GenericDaoMain<Race> {
    public RaceDao() {
        super(new Race());
    }

    public List<Race> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List races = (List) session.createQuery("from Race").list();
            session.close();
            return races;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public void deleteRace(Integer raceId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete Race where raceId = :raceId");
            query.setParameter("raceId", raceId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("deleteRace(raceId) Error = " + e.getCause());
        }
    }
}
