package com.worldofzaar.dao;

import com.worldofzaar.entity.Hero;
import com.worldofzaar.util.HibernateUtilActive;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public class HeroDao extends GenericDaoActive<Hero> {

    public HeroDao() {
        super(new Hero());
    }

    public List<Hero> list() {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            List heroes = (List) session.createQuery("from Hero ").list();
            session.close();
            return heroes;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

}
