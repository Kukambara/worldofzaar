package com.worldofzaar.dao;

import com.worldofzaar.entity.EngRaceText;
import com.worldofzaar.entity.RuRaceText;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:11
 * To change this template use File | Settings | File Templates.
 */
public class EngRaceTextDao extends GenericDaoMain<EngRaceText> {
    public EngRaceTextDao() {
        super(new EngRaceText());
    }

    public List<RuRaceText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List engRace = (List) session.createQuery("from EngRaceText").list();
            session.close();
            return engRace;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
