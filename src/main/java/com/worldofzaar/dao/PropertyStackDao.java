package com.worldofzaar.dao;

import com.worldofzaar.entity.PropertyStack;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:45
 * To change this template use File | Settings | File Templates.
 */
public class PropertyStackDao extends GenericDaoActive<PropertyStack> {
    public PropertyStackDao(){
        super(new PropertyStack());
    }

    public List<PropertyStack> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List propertyStack = (List) session.createQuery("from PropertyStack").list();
            session.close();
            return propertyStack;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }

}
