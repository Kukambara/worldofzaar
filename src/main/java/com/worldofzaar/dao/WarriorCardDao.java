package com.worldofzaar.dao;

import com.worldofzaar.entity.ActiveCoalition;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilActive;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:06
 * To change this template use File | Settings | File Templates.
 */
public class WarriorCardDao extends GenericDaoMain<WarriorCard> {
    public WarriorCardDao() {
        super(new WarriorCard());
    }

    public List<WarriorCard> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List warriorCards = (List) session.createQuery("from WarriorCard").list();
            session.close();
            return warriorCards;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
