package com.worldofzaar.adapter;

import com.worldofzaar.entity.RaceText;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.11.13
 * Time: 12:34
 * To change this template use File | Settings | File Templates.
 */
public class RaceAdapter {
    private Integer raceId;
    private String raceName;
    private String raceDescription;
    private String racePictureUrl;

    public RaceAdapter(RaceText input) {
        this.raceId = input.getRace().getRaceId();
        this.raceName =input.getRaceName();
        this.raceDescription =input.getRaceDescription();
        this.racePictureUrl =input.getRaceNamePicturePath();
    }

    public Integer getRaceId() {
        return raceId;
    }

    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getRaceDescription() {
        return raceDescription;
    }

    public void setRaceDescription(String raceDescription) {
        this.raceDescription = raceDescription;
    }

    public String getRacePictureUrl() {
        return racePictureUrl;
    }

    public void setRacePictureUrl(String racePictureUrl) {
        this.racePictureUrl = racePictureUrl;
    }
}
