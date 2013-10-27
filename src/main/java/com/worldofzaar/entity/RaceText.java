package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"RaceTexts\"")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class RaceText {
    @Id
    @SequenceGenerator(name = "raceText_seq", sequenceName = "\"RaceTexts_raceTextId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "raceText_seq")
    @Column(name = "\"raceTextId\"")
    private Integer raceTextId;
    @OneToOne
    @JoinColumn(name = "\"raceId\"")
    private Race race;
    @Column(name = "\"raceName\"")
    private String raceName;
    @Column(name = "\"raceDescription\"")
    private String raceDescription;

    public Integer getRaceTextId() {
        return raceTextId;
    }

    public void setRaceTextId(Integer raceTextId) {
        this.raceTextId = raceTextId;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
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
}
