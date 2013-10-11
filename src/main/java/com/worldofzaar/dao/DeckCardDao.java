package com.worldofzaar.dao;

import com.worldofzaar.entity.CoalitionRequest;
import com.worldofzaar.entity.DeckCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class DeckCardDao  extends GenericDaoMain<DeckCard> {

    public DeckCardDao() {
        super(new DeckCard());
    }

    public List<DeckCard> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List deckCards = (List) session.createQuery("from DeckCard ").list();
            session.close();
            return deckCards;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}