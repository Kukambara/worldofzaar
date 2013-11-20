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

    public void createRace(String ruName, String ruDescription, String ruPicture,
                           String engName, String engDescription, String engPicture) {
        RaceDao raceDao = new RaceDao();
        Race race = new Race();
        // Crate new race.
        raceDao.add(race);
        // Create RuRaceText for race.
        RuRaceTextService ruRaceTextService = new RuRaceTextService();
        ruRaceTextService.createRuRaceText(race, ruName, ruDescription, ruPicture);
        // Create EngRaceText for race.
        EngRaceTextService engRaceTextService = new EngRaceTextService();
        engRaceTextService.createEngRaceText(race, engName, engDescription, engPicture);

    }

    public void deleteRace(Integer raceId) {
        EngRaceTextService engRaceTextService = new EngRaceTextService();
        engRaceTextService.deleteRaceText(raceId);
        RuRaceTextService ruRaceTextService = new RuRaceTextService();
        ruRaceTextService.deleteRaceText(raceId);
        RaceDao raceDao = new RaceDao();
        raceDao.deleteRace(raceId);
    }

    public void editRace(Integer raceId, String ruName, String ruDescription, String ruPicture,
                         String engName, String engDescription, String engPicture) {
        //Edit engRaceText
        EngRaceTextService engRaceTextService = new EngRaceTextService();
        engRaceTextService.editEngRaceText(raceId, engName, engDescription, engPicture);
        //Edit ruRaceText
        RuRaceTextService ruRaceTextService = new RuRaceTextService();
        ruRaceTextService.editRuRaceText(raceId, ruName, ruDescription, ruPicture);


    }

    public Race getRaceById(Integer raceId) {
        RaceDao raceDao = new RaceDao();
        return raceDao.find(raceId);
    }
}
