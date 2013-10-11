package com.worldofzaar.dao;

import com.worldofzaar.entity.ActiveCoalition;
import com.worldofzaar.entity.ActiveWarriorCard;
import com.worldofzaar.util.HibernateUtilActive;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 14:16
 * To change this template use File | Settings | File Templates.
 */
public class ActiveWarriorCardDao extends GenericDaoActive<ActiveWarriorCard> {

    public ActiveWarriorCardDao() {
        super(new ActiveWarriorCard());
    }

    public List<ActiveWarriorCard> list() {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            List activeWarriorCards= (List) session.createQuery("from ActiveWarriorCard ").list();
            session.close();
            return activeWarriorCards;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
