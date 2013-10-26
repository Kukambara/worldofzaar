package com.worldofzaar.dao;

import com.worldofzaar.entity.RuRaceText;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
public class RuRaceTextDao extends GenericDaoMain<RuRaceText> {
    public RuRaceTextDao() {
        super(new RuRaceText());
    }

    public List<RuRaceText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List ruRace = (List) session.createQuery("from RuRaceText").list();
            session.close();
            return ruRace;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
