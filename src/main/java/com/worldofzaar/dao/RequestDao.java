package com.worldofzaar.dao;

import com.worldofzaar.entity.Request;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
public class RequestDao extends GenericDaoMain<Request> {
    public RequestDao() {
        super(new Request());
    }

    public List<Request> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List requests = (List) session.createQuery("from Request").list();
            session.close();
            return requests;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
