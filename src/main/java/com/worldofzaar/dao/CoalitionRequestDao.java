package com.worldofzaar.dao;

import com.worldofzaar.entity.CoalitionRequest;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
public class CoalitionRequestDao extends GenericDaoMain<CoalitionRequest> {

    public CoalitionRequestDao() {
        super(new CoalitionRequest());
    }

    public List<CoalitionRequest> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List coalitionRequests = (List) session.createQuery("from CoalitionRequest ").list();
            session.close();
            return coalitionRequests;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
