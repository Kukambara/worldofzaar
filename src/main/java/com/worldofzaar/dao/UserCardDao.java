package com.worldofzaar.dao;

import com.worldofzaar.entity.UserCard;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class UserCardDao extends GenericDaoMain<UserCard> {
    public UserCardDao() {
        super(new UserCard());
    }

    public List<UserCard> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List userCards = (List) session.createQuery("from UserCard").list();
            session.close();
            return userCards;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public List<UserCard> gelAllUserCardsById(Integer userId) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from UserCard where user.userId = :userId");
            query.setParameter("userId", userId);
            List userCards = query.list();
            session.close();
            return userCards;
        } catch (Exception e) {
            System.out.println("gelAllUserCardsById(userId) Error = " + e.getCause());
        }
        return null;
    }

}
