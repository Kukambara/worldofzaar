package com.worldofzaar.dao;

import com.worldofzaar.entity.MyGame;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 */
public class MyGameDao extends GenericDaoMain<MyGame> {

    public MyGameDao() {
        super(new MyGame());
    }

    public List<MyGame> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List myGames = (List) session.createQuery("from MyGame ").list();
            session.close();
            return myGames;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
