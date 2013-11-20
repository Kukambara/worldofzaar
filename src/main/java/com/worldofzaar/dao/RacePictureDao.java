package com.worldofzaar.dao;

import com.worldofzaar.entity.RacePicture;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */
public class RacePictureDao extends GenericDaoMain<RacePicture> {
    public RacePictureDao() {
        super(new RacePicture());
    }

    public List<RacePicture> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List racePictures = (List) session.createQuery("from RacePicture").list();
            session.close();
            return racePictures;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public List<RacePicture> geRacePicturesByRaceId(Integer raceId) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from RacePicture where race.raceId = :raceId");
            query.setParameter("raceId", raceId);
            List<RacePicture> racePictures = query.list();
            session.close();
            return racePictures;
        } catch (Exception e) {
            System.out.println("geRacePicturesByRaceId(Integer raceId) Error = " + e.getCause());
        }
        return null;
    }

    public void removeRacePictureById(Integer racePictureId) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete RacePicture where racePictureId = :racePictureId");
            query.setParameter("racePictureId", racePictureId);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("removeRacePictureById(Integer racePictureId) Error = " + e.getCause());
        }
    }

    public Integer getRaceIdByRacePictureId(Integer id) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("select race.raceId from RacePicture where racePictureId = :id");
            query.setParameter("id", id);
            query.setMaxResults(1);
            List race = query.list();
            session.close();
            return (Integer) race.get(0);
        } catch (Exception e) {
            System.out.println("getUserByEmail(email) Error = " + e.getCause());
        }
        return null;
    }

}
