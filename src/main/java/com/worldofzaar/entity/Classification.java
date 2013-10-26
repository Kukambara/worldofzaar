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
@Table(name = "\"Classifications\"")
public class Classification {
    @Id
    @SequenceGenerator(name = "classification_seq", sequenceName = "\"Classifications_classId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classification_seq")
    @Column(name = "\"classId\"")
    private Integer classificationId;
    @ManyToOne
    @JoinColumn(name = "\"raceId\"")
    private Race race;

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
