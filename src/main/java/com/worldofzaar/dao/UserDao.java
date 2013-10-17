package com.worldofzaar.dao;

import com.worldofzaar.entity.User;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 */
public class UserDao extends GenericDaoMain<User> {
    public UserDao() {
        super(new User());
    }

    public List<User> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List users = (List) session.createQuery("from User").list();
            session.close();
            return users;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
