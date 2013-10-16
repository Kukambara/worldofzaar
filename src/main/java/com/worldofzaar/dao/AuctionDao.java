package com.worldofzaar.dao;

import com.worldofzaar.entity.Auction;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class AuctionDao extends GenericDaoMain<Auction> {

    public AuctionDao() {
        super(new Auction());
    }

    public List<Auction> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List auctions = (List) session.createQuery("from Auction ").list();
            session.close();
            return auctions;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
