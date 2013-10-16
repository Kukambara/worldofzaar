package com.worldofzaar.dao;

import com.worldofzaar.entity.Friendship;
import com.worldofzaar.entity.MasterOfDeck;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public class MasterOfDeckDao extends GenericDaoMain<MasterOfDeck> {

    public MasterOfDeckDao() {
        super(new MasterOfDeck());
    }

    public List<MasterOfDeck> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List masterOfDecks = (List) session.createQuery("from MasterOfDeck ").list();
            session.close();
            return masterOfDecks;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
