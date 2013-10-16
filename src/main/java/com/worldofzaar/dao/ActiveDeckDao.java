package com.worldofzaar.dao;

import com.worldofzaar.entity.ActiveDeck;
import com.worldofzaar.util.HibernateUtilActive;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class ActiveDeckDao extends GenericDaoActive<ActiveDeck> {

    public ActiveDeckDao() {
        super(new ActiveDeck());
    }

    public List<ActiveDeck> list() {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            List activeDecks = (List) session.createQuery("from ActiveDeck").list();
            session.close();
            return activeDecks;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
