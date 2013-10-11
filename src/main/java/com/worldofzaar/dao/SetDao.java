package com.worldofzaar.dao;

import com.worldofzaar.entity.Set;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class SetDao extends GenericDaoMain<Set> {
    public SetDao() {
        super(new Set());
    }

    public List<Set> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List sets = (List) session.createQuery("from Set").list();
            session.close();
            return sets;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
