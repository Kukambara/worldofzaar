package com.worldofzaar.service;

import com.worldofzaar.dao.WebUserDao;
import com.worldofzaar.entity.User;
import com.worldofzaar.entity.WebUser;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 30.11.13
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */
public class WebUserService {

    public void setUser(Integer webUserId, User user) {
        WebUserDao webUserDao = new WebUserDao();
        WebUser webUser = webUserDao.find(webUserId);
        webUser.setUser(user);
        webUserDao.update(webUser);
    }
}
