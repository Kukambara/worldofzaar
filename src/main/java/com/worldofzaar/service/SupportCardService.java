package com.worldofzaar.service;

import com.worldofzaar.adapter.SupportCardAdapter;
import com.worldofzaar.dao.SupportCardDao;
import com.worldofzaar.entity.SupportCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class SupportCardService {

    public List<SupportCardAdapter> getCompositeSupportCards(String lang) {
        SupportCardDao supportCardDao = new SupportCardDao();
        List<Object[]> supportCards = supportCardDao.getCompositeSupportCards(lang);
        List<SupportCardAdapter> supportCardAdapter = new ArrayList<SupportCardAdapter>();
        for (Object[] tmp : supportCards) {
            supportCardAdapter.add(new SupportCardAdapter(tmp));
        }
        return supportCardAdapter;
    }

    public List<SupportCard> getList() {
        SupportCardDao supportCardDao = new SupportCardDao();
        return supportCardDao.list();
    }
}
