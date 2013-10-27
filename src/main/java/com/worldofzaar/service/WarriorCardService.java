package com.worldofzaar.service;

import com.worldofzaar.adapter.WarriorCardAdapter;
import com.worldofzaar.dao.WarriorCardDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */
public class WarriorCardService {

    public List<WarriorCardAdapter> getCompositeWarriorsCards(String lang){
        WarriorCardDao warriorCardDao = new WarriorCardDao();
        List<Object[]> warriorCards = warriorCardDao.getCompositeWarriorsCards(lang);

        List<WarriorCardAdapter> warriorCardAdapter = new ArrayList<WarriorCardAdapter>();
        for(Object[] tmp: warriorCards){
            warriorCardAdapter.add(new WarriorCardAdapter(tmp));
        }
        return warriorCardAdapter;
    }
}
