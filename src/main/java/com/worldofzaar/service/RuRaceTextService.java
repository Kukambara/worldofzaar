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
    public void createRuRaceText(Race race, String name, String description) {
        RuRaceTextDao ruRaceTextDao = new RuRaceTextDao();
        RuRaceText ruRaceText = new RuRaceText();
        ruRaceText.setRace(race);
        ruRaceText.setRaceName(name);
        ruRaceText.setRaceDescription(description);
        ruRaceTextDao.add(ruRaceText);
    }
}
