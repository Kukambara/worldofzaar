package com.worldofzaar.service;

import com.worldofzaar.dao.EngSetTextDao;
import com.worldofzaar.entity.EngSetText;
import com.worldofzaar.entity.Set;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class EngSetTextService {
    public List<EngSetText> getList() {
        EngSetTextDao engSetTextDao = new EngSetTextDao();
        return engSetTextDao.list();

    }

    public void createText(String name, String description, Set set) {
        EngSetTextDao engSetTextDao = new EngSetTextDao();
        EngSetText engSetText = new EngSetText();
        engSetText.setSetName(name);
        engSetText.setSetInfo(description);
        engSetText.setSet(set);
        engSetTextDao.add(engSetText);
    }

    public void editText(String name, String description, Set set) {
        EngSetTextDao engSetTextDao = new EngSetTextDao();
        EngSetText engSetText = engSetTextDao.getTextBySetId(set.getSetId());
        engSetText.setSetName(name);
        engSetText.setSetInfo(description);
        engSetTextDao.update(engSetText);
    }

    public EngSetText getTextBySetId(Integer setId) {
        EngSetTextDao engSetTextDao = new EngSetTextDao();
        return engSetTextDao.getTextBySetId(setId);
    }

    public void deleteSetText(Integer setId) {
        EngSetTextDao engSetTextDao = new EngSetTextDao();
        engSetTextDao.deleteText(setId);
    }


}
