package com.worldofzaar.dao;

import com.worldofzaar.entity.MasterOfDeck;
import com.worldofzaar.entity.Message;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
public class MessageDao extends GenericDaoMain<Message> {

    public MessageDao() {
        super(new Message());
    }

    public List<Message> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List messages = (List) session.createQuery("from Message ").list();
            session.close();
            return messages;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
