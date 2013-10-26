package com.worldofzaar.dao;

import com.worldofzaar.entity.RuClassText;
import com.worldofzaar.entity.RuRaceText;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class RuClassTextDao extends GenericDaoMain<RuClassText> {
    public RuClassTextDao() {
        super(new RuClassText());
    }

    public List<RuRaceText> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List ruClass = (List) session.createQuery("from RuRaceText").list();
            session.close();
            return ruClass;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
