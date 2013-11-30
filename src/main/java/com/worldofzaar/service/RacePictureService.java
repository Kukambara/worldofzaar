package com.worldofzaar.service;

import com.worldofzaar.dao.RacePictureDao;
import com.worldofzaar.entity.Race;
import com.worldofzaar.entity.RacePicture;
import com.worldofzaar.util.FileManager;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:35
 * To change this template use File | Settings | File Templates.
 */
public class RacePictureService {

    public List<RacePicture> getAll() {
        RacePictureDao racePictureDao = new RacePictureDao();
        return racePictureDao.list();
    }

    public void addPictures(MultipartHttpServletRequest request, Boolean isMale, ServletContext context, Integer raceId) {
        Iterator<String> iter = request.getFileNames();
        FileManager fileManager = new FileManager();
        RaceService raceService = new RaceService();
        Race race = raceService.getRaceById(raceId);
        RacePictureDao racePictureDao = new RacePictureDao();
        while (iter.hasNext()) {
            String fileName = iter.next();
            MultipartFile multipartFile = request.getFile(fileName);
            //Upload file and get it path.
            String filePath = fileManager.upload(multipartFile, context);
            //Create racePicture.
            RacePicture racePicture = new RacePicture();
            racePicture.setMale(isMale);
            racePicture.setPicturePath(filePath);
            racePicture.setRace(race);
            racePictureDao.add(racePicture);
        }
    }

    public List<RacePicture> getRacePicturesByRaceId(Integer raceId) {
        RacePictureDao racePictureDao = new RacePictureDao();
        return racePictureDao.geRacePicturesByRaceId(raceId);
    }

    public Integer setNewSex(Integer racePictureId) {
        RacePictureDao racePictureDao = new RacePictureDao();
        RacePicture racePicture = racePictureDao.find(racePictureId);
        racePicture.setMale(!racePicture.getMale());
        racePictureDao.update(racePicture);
        return racePicture.getRace().getRaceId();
    }

    public void deleteRacePictureById(Integer racePictureId) {
        RacePictureDao racePictureDao = new RacePictureDao();
        racePictureDao.removeRacePictureById(racePictureId);
    }

    public Integer getRaceIdByRacePictureId(Integer id) {
        RacePictureDao racePictureDao = new RacePictureDao();
        return racePictureDao.getRaceIdByRacePictureId(id);
    }
}
