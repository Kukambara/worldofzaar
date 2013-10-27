package com.worldofzaar.service;

import com.worldofzaar.dao.ClassificationDao;
import com.worldofzaar.entity.Classification;
import com.worldofzaar.entity.Race;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:42
 * To change this template use File | Settings | File Templates.
 */
public class ClassificationService {
    public List<Classification> getAllClasses() {
        ClassificationDao classificationDao = new ClassificationDao();
        return classificationDao.list();
    }

    public void createClass(String ruName, String ruDescription,
                            String engName, String engDescription, String raceId) {
        //Find race.
        RaceService raceService = new RaceService();
        Race race = raceService.getRaceById(Integer.valueOf(raceId));
        //Add classification.
        Classification classification = new Classification();
        ClassificationDao classificationDao = new ClassificationDao();
        classification.setRace(race);
        classificationDao.add(classification);
        //Add RuClassText for classification.
        RuClassTextService ruClassTextService = new RuClassTextService();
        ruClassTextService.createRuClassText(classification, ruName, ruDescription);
        //Add EngClassText for classification.
        EngClassTextService engClassTextService = new EngClassTextService();
        engClassTextService.createEngClassText(classification, engName, engDescription);

    }
}
