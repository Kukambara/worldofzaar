package com.worldofzaar.dao;

import com.worldofzaar.entity.Hero;
import com.worldofzaar.entity.Log;
import com.worldofzaar.util.HibernateUtilActive;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
public class LogDao extends GenericDaoActive<Log> {

    public LogDao() {
        super(new Log());
    }

    public List<Log> list() {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            List logs = (List) session.createQuery("from Log ").list();
            session.close();
            return logs;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
