package com.worldofzaar.dao;

import com.worldofzaar.entity.Cloth;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 14.10.13
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
public class ClothDao extends GenericDaoMain<Cloth> {

    public ClothDao() {
        super(new Cloth());
    }

    public List<Cloth> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List clothes = (List) session.createQuery("from Cloth ").list();
            session.close();
            return clothes;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
