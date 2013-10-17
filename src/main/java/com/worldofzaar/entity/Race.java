package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:10
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Races\"")
public class Race {
    @Id
    @SequenceGenerator(name = "race_seq", sequenceName = "\"Races_raceId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "race_seq")
    @Column(name = "\"raceId\"")
    private Integer raceId;
    @Column(name = "\"raceName\"")
    private String raceName;
    @Column(name = "\"raceInfo\"")
    private String raceInfo;

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

    public String getRaceInfo() {
        return raceInfo;
    }

    public void setRaceInfo(String raceInfo) {
        this.raceInfo = raceInfo;
    }
}
