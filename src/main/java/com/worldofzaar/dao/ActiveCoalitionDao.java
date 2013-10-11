package com.worldofzaar.dao;

import com.worldofzaar.entity.ActiveCoalition;
import com.worldofzaar.util.HibernateUtilActive;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class ActiveCoalitionDao extends GenericDaoActive<ActiveCoalition> {
    public ActiveCoalitionDao() {
        super(new ActiveCoalition());
    }

    public List<ActiveCoalition> list() {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            List activeCoalitions = (List) session.createQuery("from ActiveCoalition").list();
            session.close();
            return activeCoalitions;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
