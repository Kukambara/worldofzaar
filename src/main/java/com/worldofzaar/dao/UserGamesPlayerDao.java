package com.worldofzaar.dao;

import com.worldofzaar.entity.UserGamesPlayer;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
public class UserGamesPlayerDao extends GenericDaoMain<UserGamesPlayer> {
    public UserGamesPlayerDao() {
        super(new UserGamesPlayer());
    }

    public List<UserGamesPlayer> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List userGamesPlayer = (List) session.createQuery("from UserGamesPlayer").list();
            session.close();
            return userGamesPlayer;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}

