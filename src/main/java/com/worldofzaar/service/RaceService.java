package com.worldofzaar.service;

import com.worldofzaar.dao.RaceDao;
import com.worldofzaar.entity.Race;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
public class RaceService {

    public List<Race> getAllRace() {
        RaceDao raceDao = new RaceDao();
        return raceDao.list();
    }

    public void createRace(String ruName, String ruDescription,
                           String engName, String engDescription) {
        RaceDao raceDao = new RaceDao();
        Race race = new Race();
        // Crate new race.
        raceDao.add(race);
        // Create RuRaceText for race.
        RuRaceTextService ruRaceTextService = new RuRaceTextService();
        ruRaceTextService.createRuRaceText(race, ruName, ruDescription);
        // Create EngRaceText for race.
        EngRaceTextService engRaceTextService = new EngRaceTextService();
        engRaceTextService.createEngRaceText(race, engName, engDescription);

    }

    public Race getRaceById(Integer raceId) {
        RaceDao raceDao = new RaceDao();
        return raceDao.find(raceId);
    }
}
