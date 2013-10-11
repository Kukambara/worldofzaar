package com.worldofzaar.dao;

import com.worldofzaar.entity.EngCardText;
import com.worldofzaar.entity.EngPropertyText;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:27
 * To change this template use File | Settings | File Templates.
 */
public class EngPropertyTextDao extends GenericDaoMain<EngPropertyText> {

    public EngPropertyTextDao() {
        super(new EngPropertyText());
    }

    public List<EngPropertyText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List engPropertyTexts = (List) session.createQuery("from EngPropertyText ").list();
            session.close();
            return engPropertyTexts;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
