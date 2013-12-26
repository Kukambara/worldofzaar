package com.worldofzaar.dao;

import com.worldofzaar.entity.WebUser;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 13.10.13
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
public class WebUserDao extends GenericDaoMain<WebUser> {
    public WebUserDao() {
        super(new WebUser());
    }

    public List<WebUser> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List webUsers = (List) session.createQuery("from WebUser").list();
            session.close();
            return webUsers;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public WebUser signInEmail(String email, String pass) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from WebUser where webUserEmail = :email and webUserPass= :pass");
            query.setParameter("pass", pass);
            query.setParameter("email", email);
            query.setMaxResults(1);
            List users = query.list();
            session.close();
            if (users == null)
                return null;
            else
                return (WebUser) users.get(0);
        } catch (Exception e) {
            System.out.println("signInEmail(email, pass) Error = " + e.getCause());
        }
        return null;
    }

    public WebUser getWebUserByEmail(String email) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from WebUser where webUserEmail = :email");
            query.setParameter("email", email);
            query.setMaxResults(1);
            List users = query.list();
            session.close();
            if (users == null)
                return null;
            else
                return (WebUser) users.get(0);
        } catch (Exception e) {
            System.out.println("getUserByEmail(email) Error = " + e.getCause());
        }
        return null;
    }

}
