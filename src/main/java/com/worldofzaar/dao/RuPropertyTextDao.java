package com.worldofzaar.dao;

import com.worldofzaar.entity.RuCardText;
import com.worldofzaar.entity.RuPropertyText;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
public class RuPropertyTextDao extends GenericDaoMain<RuPropertyText> {
    public RuPropertyTextDao(){
        super(new RuPropertyText());
    }

    public List<RuPropertyText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List ruProperty = (List) session.createQuery("from RuPropertyText").list();
            session.close();
            return ruProperty;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
