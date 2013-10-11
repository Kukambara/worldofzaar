package com.worldofzaar.dao;

import com.worldofzaar.entity.RuSetText;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public class RuSetTextDao extends GenericDaoMain<RuSetText> {
    public RuSetTextDao(){
        super(new RuSetText());
    }

    public List<RuSetText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List ruSetTexts = (List) session.createQuery("from RuSetText").list();
            session.close();
            return ruSetTexts;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
