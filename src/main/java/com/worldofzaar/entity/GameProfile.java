package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "GameProfiles")
public class GameProfile {
    @Id
    @GeneratedValue
    @Column(name = "gameProfileId")
    Integer gameProfileId;
    @ManyToOne
    @JoinColumn(name = "raceId")
    Race race;
    @ManyToOne
    @JoinColumn(name = "racePictureId")
    RacePicture racePicture;
    @ManyToOne
    @JoinColumn(name = "classId")
    Classification classification;
    @ManyToOne
    @JoinColumn(name = "blazonId")
    Blazon blazon;
    @Column(name = "level")
    Integer level;
    @Column(name = "experience")
    Integer experience;
    @Column(name = "money")
    Integer money;
    @Column(name = "donateMoney")
    Integer donateMoney;
    @Column(name = "energy")
    Integer energy;
    @Column(name = "isMale")
    Boolean isMale;

    public Integer getGameProfileId() {
        return gameProfileId;
    }

    public void setGameProfileId(Integer gameProfileId) {
        this.gameProfileId = gameProfileId;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public RacePicture getRacePicture() {
        return racePicture;
    }

    public void setRacePicture(RacePicture racePicture) {
        this.racePicture = racePicture;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Blazon getBlazon() {
        return blazon;
    }

    public void setBlazon(Blazon blazon) {
        this.blazon = blazon;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getDonateMoney() {
        return donateMoney;
    }

    public void setDonateMoney(Integer donateMoney) {
        this.donateMoney = donateMoney;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Boolean getMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }
}
