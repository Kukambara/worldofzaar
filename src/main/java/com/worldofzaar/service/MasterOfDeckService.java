package com.worldofzaar.service;

import com.worldofzaar.dao.MasterOfDeckDao;
import com.worldofzaar.entity.MasterOfDeck;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class MasterOfDeckService {

    public List<MasterOfDeck> getAllPrice(){
        MasterOfDeckDao masterOfDeckDao = new MasterOfDeckDao();
        return masterOfDeckDao.list();
    }
}
