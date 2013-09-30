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
@Table(name = "Classifications")
public class Classification {
    @Id
    @GeneratedValue
    @Column(name = "classId")
    Integer classificationId;
    @ManyToOne
    @JoinColumn(name = "raceId")
    Race race;
    @Column(name = "className")
    String className;
    @Column(name = "classInfo")
    String classInfo;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }
}
