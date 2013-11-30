package com.worldofzaar.service;

import com.worldofzaar.dao.UserDao;
import com.worldofzaar.entity.GameProfile;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class UserService {

    public GameProfile getUserGameProfileById(Integer userId) {
        UserDao gameProfileDao = new UserDao();
        return gameProfileDao.getUserGameProfilesById(userId);
    }

}
