package com.worldofzaar.dao;

import com.worldofzaar.entity.GameProfile;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */
public class GameProfileDao extends GenericDaoMain<GameProfile> {

    public GameProfileDao() {
        super(new GameProfile());
    }

    public List<GameProfile> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List gameProfiles = (List) session.createQuery("from GameProfile ").list();
            session.close();
            return gameProfiles;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
