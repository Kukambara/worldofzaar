package com.worldofzaar.dao;

import com.worldofzaar.entity.CoalitionRequest;
import com.worldofzaar.entity.Deck;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class DeckDao extends GenericDaoMain<Deck> {

    public DeckDao() {
        super(new Deck());
    }

    public List<Deck> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List decks = (List) session.createQuery("from Deck ").list();
            session.close();
            return decks;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public List<Deck> getUserDecksById(Integer userId) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from Deck where user.userId = :userId");
            query.setParameter("userId", userId);
            List decks = query.list();
            session.close();
            return decks;
        } catch (Exception e) {
            System.out.println("getUserDecksById(userId) Error = " + e.getCause());
        }
        return null;
    }
}
