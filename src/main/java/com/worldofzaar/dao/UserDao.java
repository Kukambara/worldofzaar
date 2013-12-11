package com.worldofzaar.dao;

import com.worldofzaar.entity.GameProfile;
import com.worldofzaar.entity.User;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 */
public class UserDao extends GenericDaoMain<User> {
    public UserDao() {
        super(new User());
    }

    public List<User> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List users = (List) session.createQuery("from User").list();
            session.close();
            return users;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public Boolean checkUserNickname(String nickname) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from User as u where u.userName = :nickname");
            query.setParameter("nickname", nickname);
            query.setMaxResults(1);
            List<User> user = query.list();
            session.close();
            if(user.isEmpty())  return true;
            return false;
        } catch (Exception e) {
            System.out.println("getUserGameProfilesById(userId) Error = " + e.getCause());
        }
        return null;
    }

    public GameProfile getUserGameProfilesById(Integer userId) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("select GameProfile from User where User.userId = :userId");
            query.setParameter("userId", userId);
            query.setMaxResults(1);
            List<GameProfile> gameProfile = query.list();
            session.close();
            return gameProfile.get(0);
        } catch (Exception e) {
            System.out.println("getUserGameProfilesById(userId) Error = " + e.getCause());
        }
        return null;
    }
}
