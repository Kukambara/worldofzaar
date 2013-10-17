package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:13
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"RacePictures\"")
public class RacePicture {
    @Id
    @SequenceGenerator(name = "racePicture_seq", sequenceName = "\"RacePictures_racePicture_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "racePicture_seq")
    @Column(name = "\"racePictureId\"")
    private Integer racePictureId;
    @ManyToOne
    @JoinColumn(name = "\"raceId\"")
    private Race race;
    @Column(name = "\"picturePath\"")
    private String picturePath;
    @Column(name = "\"isMale\"")
    private Boolean isMale;

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
