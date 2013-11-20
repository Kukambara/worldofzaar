package com.worldofzaar.service;

import com.worldofzaar.dao.RuRaceTextDao;
import com.worldofzaar.entity.Race;
import com.worldofzaar.entity.RuRaceText;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
public class RuRaceTextService {
    public void createRuRaceText(Race race, String ruName, String ruDescription, String ruPicture) {
        RuRaceTextDao ruRaceTextDao = new RuRaceTextDao();
        RuRaceText ruRaceText = new RuRaceText();
        ruRaceText.setRace(race);
        ruRaceText.setRaceName(ruName);
        ruRaceText.setRaceDescription(ruDescription);
        ruRaceText.setRaceNamePicturePath(ruPicture);
        ruRaceTextDao.add(ruRaceText);
    }

    public void editRuRaceText(Integer raceId, String ruName, String ruDescription, String ruPicture) {
        RuRaceTextDao ruRaceTextDao = new RuRaceTextDao();
        RuRaceText ruRaceText = ruRaceTextDao.getTextByRaceId(raceId);
        ruRaceText.setRaceName(ruName);
        ruRaceText.setRaceDescription(ruDescription);
        if (!ruPicture.equals(""))
            ruRaceText.setRaceNamePicturePath(ruPicture);
        ruRaceTextDao.update(ruRaceText);
    }

    public RuRaceText getTextsByRaceId(Integer id) {
        RuRaceTextDao ruRaceTextDao = new RuRaceTextDao();
        return ruRaceTextDao.getTextByRaceId(id);
    }

    public void deleteRaceText(Integer raceId) {
        RuRaceTextDao ruRaceTextDao = new RuRaceTextDao();
        ruRaceTextDao.deleteRaceText(raceId);

    }

}
