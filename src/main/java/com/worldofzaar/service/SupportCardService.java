package com.worldofzaar.service;

import com.worldofzaar.dao.SupportCardDao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class SupportCardService {

    public List<Object[]> getCompositeSupportCards(String lang){
        SupportCardDao supportCardDao = new SupportCardDao();
        return supportCardDao.getCompositeSupportCards(lang);
    }
}
