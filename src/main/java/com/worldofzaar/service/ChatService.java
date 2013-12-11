package com.worldofzaar.service;

import com.worldofzaar.dao.ChatDao;
import com.worldofzaar.entity.Chat;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:42
 * To change this template use File | Settings | File Templates.
 */
public class ChatService {
    public Chat createChat() {
        ChatDao chatDao = new ChatDao();
        Chat chat = new Chat();
        chatDao.add(chat);
        return chat;
    }
}
