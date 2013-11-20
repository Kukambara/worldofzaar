package com.worldofzaar.dao;

import com.worldofzaar.entity.Property;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
public class PropertyDao extends GenericDaoMain<Property> {
    public PropertyDao() {
        super(new Property());
    }

    public List<Property> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List properties = (List) session.createQuery("from Property").list();
            session.close();
            return properties;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

    public void remove(Integer id) {
        Transaction tx = null;
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete Property where propertyId = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("remove(id) Error = " + e.getCause());
        }
    }
}
