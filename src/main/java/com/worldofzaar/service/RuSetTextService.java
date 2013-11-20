package com.worldofzaar.service;

import com.worldofzaar.dao.RuSetTextDao;
import com.worldofzaar.entity.RuSetText;
import com.worldofzaar.entity.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:33
 * To change this template use File | Settings | File Templates.
 */
public class RuSetTextService {

    public void createText(String name, String description, Set set) {
        RuSetTextDao ruSetTextDao = new RuSetTextDao();
        RuSetText ruSetText = new RuSetText();
        ruSetText.setSetName(name);
        ruSetText.setSetInfo(description);
        ruSetText.setSet(set);
        ruSetTextDao.add(ruSetText);
    }

    public void editText(String name, String description, Set set) {
        RuSetTextDao ruSetTextDao = new RuSetTextDao();
        RuSetText ruSetText = ruSetTextDao.getTextBySetId(set.getSetId());
        ruSetText.setSetName(name);
        ruSetText.setSetInfo(description);
        ruSetTextDao.update(ruSetText);
    }

    public RuSetText getTextBySetId(Integer setId) {
        RuSetTextDao ruSetTextDao = new RuSetTextDao();
        return ruSetTextDao.getTextBySetId(setId);
    }

    public void deleteSetText(Integer setId) {
        RuSetTextDao ruSetTextDao = new RuSetTextDao();
        ruSetTextDao.deleteText(setId);
    }
}
