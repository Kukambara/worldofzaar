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
    public void createEngRaceText(Race race, String engName, String engDescription, String engPicture) {
        EngRaceTextDao engRaceTextDao = new EngRaceTextDao();
        EngRaceText engRaceText = new EngRaceText();
        engRaceText.setRace(race);
        engRaceText.setRaceName(engName);
        engRaceText.setRaceDescription(engDescription);
        engRaceText.setRaceNamePicturePath(engPicture);
        engRaceTextDao.add(engRaceText);
    }

    public void deleteRaceText(Integer raceId) {
        EngRaceTextDao engRaceTextDao = new EngRaceTextDao();
        engRaceTextDao.deleteRaceText(raceId);
    }

    public void editEngRaceText(Integer raceId, String engName, String engDescription, String engPicture) {
        EngRaceTextDao engRaceTextDao = new EngRaceTextDao();
        EngRaceText engRaceText = engRaceTextDao.getTextByRaceId(raceId);
        engRaceText.setRaceName(engName);
        engRaceText.setRaceDescription(engDescription);
        if (!engPicture.equals(""))
            engRaceText.setRaceNamePicturePath(engPicture);
        engRaceTextDao.update(engRaceText);
    }

    public List<EngRaceText> getAllEngRaceTexts() {
        EngRaceTextDao engRaceTextDao = new EngRaceTextDao();
        return engRaceTextDao.list();
    }

    public EngRaceText getTextsByRaceId(Integer id) {
        EngRaceTextDao engRaceTextDao = new EngRaceTextDao();
        return engRaceTextDao.getTextByRaceId(id);
    }

}
