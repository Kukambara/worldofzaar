package com.worldofzaar.dao;

import com.worldofzaar.entity.SetText;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
public class SetTextDao extends GenericDaoMain<SetText> {
    public SetTextDao() {
        super(new SetText());
    }

    public List<SetText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List setTexts = (List) session.createQuery("from SetText").list();
            session.close();
            return setTexts;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
