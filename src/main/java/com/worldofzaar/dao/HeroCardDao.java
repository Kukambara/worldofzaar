package com.worldofzaar.dao;

import com.worldofzaar.entity.HeroCard;
import com.worldofzaar.util.HibernateUtilActive;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 */
public class HeroCardDao extends GenericDaoActive<HeroCard> {

    public HeroCardDao() {
        super(new HeroCard());
    }

    public List<HeroCard> list() {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            List heroCards = (List) session.createQuery("from HeroCard ").list();
            session.close();
            return heroCards;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}