package com.worldofzaar.dao;

import com.worldofzaar.entity.Classification;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:17
 * To change this template use File | Settings | File Templates.
 */
public class ClassificationDao extends GenericDaoMain<Classification> {

    public ClassificationDao() {
        super(new Classification());
    }

    public List<Classification> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List classifications = (List) session.createQuery("from Classification ").list();
            session.close();
            return classifications;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
