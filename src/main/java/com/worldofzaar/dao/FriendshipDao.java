package com.worldofzaar.dao;

import com.worldofzaar.entity.Experience;
import com.worldofzaar.entity.Friendship;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class FriendshipDao extends GenericDaoMain<Friendship> {

    public FriendshipDao() {
        super(new Friendship());
    }

    public List<Friendship> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List friendships = (List) session.createQuery("from Friendship ").list();
            session.close();
            return friendships;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
