package com.worldofzaar.dao;

import com.worldofzaar.entity.EngClassText;
import com.worldofzaar.entity.RuRaceText;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
public class EngClassTextDao extends GenericDaoMain<EngClassText> {
    public EngClassTextDao() {
        super(new EngClassText());
    }

    public List<RuRaceText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List engClass = (List) session.createQuery("from EngClassText").list();
            session.close();
            return engClass;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
