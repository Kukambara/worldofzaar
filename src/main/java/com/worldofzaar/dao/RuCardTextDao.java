package com.worldofzaar.dao;

import com.worldofzaar.entity.RuCardText;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class RuCardTextDao extends GenericDaoMain<RuCardText> {
    public RuCardTextDao() {
        super(new RuCardText());
    }

    public List<RuCardText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List ruCardTexts = (List) session.createQuery("from RuCardText").list();
            session.close();
            return ruCardTexts;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
