package com.worldofzaar.dao;

import com.worldofzaar.entity.Game;
import com.worldofzaar.util.HibernateUtilActive;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
public class GameDao extends GenericDaoActive<Game> {
    public GameDao() {
        super(new Game());
    }

    public List<Game> list() {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            List games = (List) session.createQuery("from Game").list();
            session.close();
            return games;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
