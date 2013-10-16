package com.worldofzaar.dao;

import com.worldofzaar.entity.EngSetText;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
public class EngSetTextDao extends GenericDaoMain<EngSetText> {

    public EngSetTextDao() {
        super(new EngSetText());
    }

    public List<EngSetText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List engCardText = (List) session.createQuery("from EngSetText ").list();
            session.close();
            return engCardText;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
