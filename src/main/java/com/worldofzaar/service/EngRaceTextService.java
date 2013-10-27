package com.worldofzaar.service;

import com.worldofzaar.dao.EngRaceTextDao;
import com.worldofzaar.entity.EngRaceText;
import com.worldofzaar.entity.Race;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
public class EngRaceTextService {
    public void createEngRaceText(Race race, String engName, String engDescription) {
        EngRaceTextDao engRaceTextDao = new EngRaceTextDao();
        EngRaceText engRaceText = new EngRaceText();
        engRaceText.setRace(race);
        engRaceText.setRaceName(engName);
        engRaceText.setRaceDescription(engDescription);
        engRaceTextDao.add(engRaceText);
    }

    public List<EngRaceText> getAllEngRaceTexts() {
        EngRaceTextDao engRaceTextDao = new EngRaceTextDao();
        return engRaceTextDao.list();
    }
}
