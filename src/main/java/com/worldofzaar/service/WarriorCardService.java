package com.worldofzaar.service;

import com.worldofzaar.dao.WarriorCardDao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */
public class WarriorCardService {

    public List<Object[]> getCompositeWarriorsCards(String lang){
        WarriorCardDao warriorCardDao = new WarriorCardDao();
        return warriorCardDao.getCompositeWarriorsCards(lang);
    }
}
