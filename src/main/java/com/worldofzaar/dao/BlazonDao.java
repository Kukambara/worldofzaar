package com.worldofzaar.dao;

import com.worldofzaar.entity.Blazon;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */
public class BlazonDao extends GenericDaoMain<Blazon> {
    public BlazonDao() {
        super(new Blazon());
    }

    public List<Blazon> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List blazons = (List) session.createQuery("from Blazon").list();
            session.close();
            return blazons;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
