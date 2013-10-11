package com.worldofzaar.dao;

import com.worldofzaar.entity.EngCardText;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
public class EngCardTextDao extends GenericDaoMain<EngCardText> {

    public EngCardTextDao() {
        super(new EngCardText());
    }

    public List<EngCardText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List engCardTexts = (List) session.createQuery("from EngCardText ").list();
            session.close();
            return engCardTexts;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
