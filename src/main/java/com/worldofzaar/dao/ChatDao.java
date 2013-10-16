package com.worldofzaar.dao;

import com.worldofzaar.entity.Chat;
import com.worldofzaar.util.HibernateUtilActive;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class ChatDao extends GenericDaoActive<Chat> {
    public ChatDao() {
        super(new Chat());
    }

    public List<Chat> list() {
        try {
            Session session = HibernateUtilActive.getSessionFactory().openSession();
            List chats = (List) session.createQuery("from Chat").list();
            session.close();
            return chats;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
