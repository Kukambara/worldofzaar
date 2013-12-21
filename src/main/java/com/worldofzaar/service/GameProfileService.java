package com.worldofzaar.service;

import com.worldofzaar.dao.GameProfileDao;
import com.worldofzaar.entity.Blazon;
import com.worldofzaar.entity.GameProfile;
import com.worldofzaar.entity.RacePicture;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class GameProfileService {
    public GameProfile addGameProfile(Integer blazonId, Integer racePictureId, boolean isMale) {
        GameProfileDao gameProfileDao = new GameProfileDao();

        BlazonService blazonService = new BlazonService();
        Blazon blazon = blazonService.getBlazon(blazonId);
        RacePictureService racePictureService = new RacePictureService();
        RacePicture racePicture = racePictureService.getRacePicture(racePictureId);
        //Stop creation of gameProfile if data is incorrect.
        if (blazon.getClassification().getRace().getRaceId() != racePicture.getRace().getRaceId())
            return null;
        GameProfile gameProfile = new GameProfile();
        gameProfile.setLevel(1);
        gameProfile.setMale(isMale);
        gameProfile.setEnergy(6);
        gameProfile.setMoney(0);
        gameProfile.setDonateMoney(0);
        gameProfile.setExperience(0);
        gameProfile.setBlazon(blazon);
        gameProfile.setRacePicture(racePicture);

        gameProfileDao.add(gameProfile);

        return gameProfile;

    }

    public void updateGameProfile(GameProfile gameProfile){
        GameProfileDao gameProfileDao = new GameProfileDao();
        gameProfileDao.update(gameProfile);
    }

}
