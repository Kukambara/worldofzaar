package com.worldofzaar.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:13
 * To change this template use File | Settings | File Templates.
 */
public class RacePicture {

    Integer racePictureId;
    Race race;
    String picturePath;
    Boolean isMale;

    public Integer getRacePictureId() {
        return racePictureId;
    }

    public void setRacePictureId(Integer racePictureId) {
        this.racePictureId = racePictureId;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Boolean getMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }
}
