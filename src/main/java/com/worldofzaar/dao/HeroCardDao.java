package com.worldofzaar.dao;

import com.worldofzaar.entity.HeroCard;
import com.worldofzaar.util.HibernateUtilActive;
import com.worldofzaar.util.UserInformation;
import org.hibernate.Query;
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

    public HeroCard getHeroCards(Integer userId) {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            Query query = session.createQuery("select hc from HeroCard as hc, Hero as h where h.userId = :id and h.heroCard.heroCardsId = hc.heroCardsId");
            query.setParameter("id", userId);
            query.setMaxResults(1);
            List list = query.list();
            session.close();
            if (list == null)
                return null;
            return (HeroCard) list.get(0);
        } catch (Exception e) {
            System.out.println("gameIsReady(userInformation) Error = " + e.getCause());
        }
        return null;
    }

    public HeroCard getHeroCards(UserInformation userInformation) {
        return getHeroCards(userInformation.getUserId());
    }
}