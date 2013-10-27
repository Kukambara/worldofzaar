package com.worldofzaar.service;

import com.worldofzaar.adapter.UserCardAdapter;
import com.worldofzaar.dao.UserCardDao;
import com.worldofzaar.entity.UserCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class UserCardService {

    public List<UserCardAdapter> getAllUserCardsById(Integer userId){
        UserCardDao userCardDao = new UserCardDao();

        List<UserCard> userCards = userCardDao.gelAllUserCardsById(userId);
        List<UserCardAdapter> userCardAdapter = new ArrayList<UserCardAdapter>();

        for (UserCard tmp : userCards) {
            userCardAdapter.add(new UserCardAdapter(tmp));
        }
        return userCardAdapter;
    }
}
