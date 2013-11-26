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

    public List<Classification> getAllClassesFull(String lang) {
        ClassificationDao classificationDao = new ClassificationDao();
        return classificationDao.list();
    }

    public void createClass(String ruName, String ruDescription,
                            String engName, String engDescription, String raceId, String ruPicture, String engPicture) {
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
        ruClassTextService.createText(classification, ruName, ruDescription, ruPicture);
        //Add EngClassText for classification.
        EngClassTextService engClassTextService = new EngClassTextService();
        engClassTextService.createText(classification, engName, engDescription, engPicture);

    }

    public void editClass(Integer classId, String ruName, String ruDescription,
                          String engName, String engDescription, String raceId, String ruPicture, String engPicture) {
        //Find race.
        RaceService raceService = new RaceService();
        Race race = raceService.getRaceById(Integer.valueOf(raceId));
        //Edit classification.
        ClassificationDao classificationDao = new ClassificationDao();
        Classification classification = classificationDao.find(classId);
        classification.setRace(race);
        classificationDao.update(classification);
        //Edit RuClassText for classification.
        RuClassTextService ruClassTextService = new RuClassTextService();
        ruClassTextService.editText(classification, ruName, ruDescription, ruPicture);
        //Edit EngClassText for classification.
        EngClassTextService engClassTextService = new EngClassTextService();
        engClassTextService.editText(classification, engName, engDescription, engPicture);
    }

    public void deleteClass(Integer classId) {
        EngClassTextService engClassTextService = new EngClassTextService();
        engClassTextService.deleteText(classId);
        RuClassTextService ruClassTextService = new RuClassTextService();
        ruClassTextService.deleteText(classId);
        ClassificationDao classificationDao = new ClassificationDao();
        classificationDao.deleteClass(classId);
    }

    public Classification getClassById(Integer id) {
        ClassificationDao classificationDao = new ClassificationDao();
        return classificationDao.find(id);
    }

}
