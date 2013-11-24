package com.worldofzaar.service;

import com.worldofzaar.dao.ExperienceDao;
import com.worldofzaar.entity.Experience;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */
public class ExperienceService {

    public void addExperience(Integer level, Integer exper) {
        Experience experience = new Experience();
        experience.setLevel(level);
        experience.setLevelExperience(exper);
        ExperienceDao experienceDao = new ExperienceDao();
        experienceDao.update(experience);
    }

    public void editExperience(Integer expId, Integer level, Integer exper) {
        ExperienceDao experienceDao = new ExperienceDao();
        Experience experience = experienceDao.find(expId);
        experience.setLevel(level);
        experience.setLevelExperience(exper);
        experienceDao.update(experience);
    }

    public Experience getExperience(Integer experienceId) {
        ExperienceDao experienceDao = new ExperienceDao();
        return experienceDao.find(experienceId);
    }

    public void deleteExperience(Integer experienceId) {
        ExperienceDao experienceDao = new ExperienceDao();
        experienceDao.remove(experienceId);
    }

    public List<Experience> getList() {
        ExperienceDao experienceDao = new ExperienceDao();
        return experienceDao.list();
    }
}
