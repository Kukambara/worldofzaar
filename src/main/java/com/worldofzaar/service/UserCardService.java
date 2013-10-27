package com.worldofzaar.service;

import com.worldofzaar.dao.UserCardDao;
import com.worldofzaar.entity.UserCard;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class UserCardService {

    public List<UserCard> getAllUserCardsById(Integer userId){
        UserCardDao userCardDao = new UserCardDao();
        return userCardDao.gelAllUserCardsById(userId);
    }
}
