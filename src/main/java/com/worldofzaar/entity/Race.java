package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Race {
    @Id
    @SequenceGenerator(name = "race_seq", sequenceName = "\"Races_raceId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "race_seq")
    @Column(name = "\"raceId\"")
    private Integer raceId;

    public Integer getRaceId() {
        return raceId;
    }

    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }
}
