package com.worldofzaar.dao;

import com.worldofzaar.entity.Admin;
import com.worldofzaar.entity.WebUser;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 13.10.13
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class AdminDao extends GenericDaoMain<Admin> {

    public AdminDao() {
        super(new Admin());
    }

    public List<Admin> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List admins = (List) session.createQuery("from Admin ").list();
            session.close();
            return admins;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public Admin getAdminByWebUser(WebUser webUser) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            Query query = session.createQuery("from Admin where webUser.webUserId = :webUserId");
            query.setParameter("webUserId", webUser.getWebUserId());
            query.setMaxResults(1);
            List admin = query.list();
            session.close();
            if (admin == null)
                return null;
            else
                return (Admin) admin.get(0);
        } catch (Exception e) {
            System.out.println("signInEmail(email, pass) Error = " + e.getCause());
        }
        return null;
    }
}

