package com.worldofzaar.dao;

import com.worldofzaar.entity.EngSetText;
import com.worldofzaar.entity.Experience;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class ExperienceDao extends GenericDaoMain<Experience> {

    public ExperienceDao() {
        super(new Experience());
    }

    public List<Experience> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List experiences = (List) session.createQuery("from Experience ").list();
            session.close();
            return experiences;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
