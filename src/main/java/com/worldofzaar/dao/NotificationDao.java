package com.worldofzaar.dao;

import com.worldofzaar.entity.Notification;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public class NotificationDao extends GenericDaoMain<Notification> {
    public NotificationDao(){
        super(new Notification());
    }

    public List<Notification> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List notifications = (List) session.createQuery("from Notification").list();
            session.close();
            return notifications;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
