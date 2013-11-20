package com.worldofzaar.dao;

import com.worldofzaar.entity.EngRaceText;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:11
 * To change this template use File | Settings | File Templates.
 */
public class EngRaceTextDao extends GenericDaoMain<EngRaceText> {
    public EngRaceTextDao() {
        super(new EngRaceText());
    }

    public List<EngRaceText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List engRace = (List) session.createQuery("from EngRaceText").list();
            session.close();
            return engRace;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public EngRaceText getTextByRaceId(Integer id) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from EngRaceText where race.raceId = :raceId");
            query.setParameter("raceId", id);
            query.setMaxResults(1);
            List text = query.list();
            session.close();
            if (text == null)
                return null;
            else
                return (EngRaceText) text.get(0);
        } catch (Exception e) {
            System.out.println("getTextByRaceId(id) Error = " + e.getCause());
        }
        return null;
    }

    public void deleteRaceText(Integer raceId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete EngRaceText where race.raceId = :raceId");
            query.setParameter("raceId", raceId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("deleteRaceText(raceId) Error = " + e.getCause());
        }
    }

}
