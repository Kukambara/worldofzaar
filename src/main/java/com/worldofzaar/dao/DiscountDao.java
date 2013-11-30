package com.worldofzaar.dao;

import com.worldofzaar.entity.Discount;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.11.13
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
public class DiscountDao extends GenericDaoMain<Discount> {

    public DiscountDao() {
        super(new Discount());
    }

    public List<Discount> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List discounts = (List) session.createQuery("from Discount ").list();
            session.close();
            return discounts;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
