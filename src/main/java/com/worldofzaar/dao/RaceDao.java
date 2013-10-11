package com.worldofzaar.dao;

import com.worldofzaar.entity.Race;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public class RaceDao extends GenericDaoMain<Race> {
    public RaceDao(){
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
}
