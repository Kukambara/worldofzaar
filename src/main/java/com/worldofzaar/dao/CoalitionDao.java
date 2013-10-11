package com.worldofzaar.dao;

import com.worldofzaar.entity.Classification;
import com.worldofzaar.entity.Coalition;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:18
 * To change this template use File | Settings | File Templates.
 */
public class CoalitionDao extends GenericDaoMain<Coalition> {

    public CoalitionDao() {
        super(new Coalition());
    }

    public List<Coalition> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List coalitions = (List) session.createQuery("from Coalition ").list();
            session.close();
            return coalitions;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
