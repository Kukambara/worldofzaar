package com.worldofzaar.service;

import com.worldofzaar.dao.SetDao;
import com.worldofzaar.entity.Set;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public class SetService {
    public void createSet(String ruName, String ruDescription, String engName, String engDescription, String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SetDao setDao = new SetDao();
        Set set = new Set();
        // Crate new set.
        try {
            set.setDate(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        setDao.add(set);
        // Create ruSetText for set.
        RuSetTextService ruSetTextService = new RuSetTextService();
        ruSetTextService.createText(ruName, ruDescription, set);
        // Create engSetText for set.
        EngSetTextService engSetTextService = new EngSetTextService();
        engSetTextService.createText(engName, engDescription, set);

    }

    public void editSet(Integer setId,
                        String ruName, String ruDescription,
                        String engName, String engDescription, String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SetDao setDao = new SetDao();
        Set set = setDao.find(setId);
        // Edit set.
        try {
            set.setDate(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        setDao.update(set);
        // Edit ruSetText for set.
        RuSetTextService ruSetTextService = new RuSetTextService();
        ruSetTextService.editText(ruName, ruDescription, set);
        // Edit engSetText for set.
        EngSetTextService engSetTextService = new EngSetTextService();
        engSetTextService.editText(engName, engDescription, set);

    }

    public void deleteSet(Integer setId) {
        EngSetTextService engSetTextService = new EngSetTextService();
        engSetTextService.deleteSetText(setId);
        RuSetTextService ruSetTextService = new RuSetTextService();
        ruSetTextService.deleteSetText(setId);
        SetDao setDao = new SetDao();
        setDao.deleteSet(setId);
    }

    public Set getSetById(Integer setId) {
        SetDao setDao = new SetDao();
        return setDao.find(setId);
    }
}
