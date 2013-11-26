package com.worldofzaar.adapter;

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
    private String raceClothesUrl;

    public RaceAdapter(Integer raceId, String raceName, String raceDescription, String racePictureUrl, String raceClothesUrl) {
        this.raceId = raceId;
        this.raceName = raceName;
        this.raceDescription = raceDescription;
        this.racePictureUrl = racePictureUrl;
        this.raceClothesUrl = raceClothesUrl;
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

    public String getRaceClothesUrl() {
        return raceClothesUrl;
    }

    public void setRaceClothesUrl(String raceClothesUrl) {
        this.raceClothesUrl = raceClothesUrl;
    }
}
