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
@Table(name = "Blazons")
public class Blazon {
    @Id
    @GeneratedValue
    @Column(name = "blazonId")
    Integer blazonId;
    @ManyToOne
    @JoinColumn(name = "classId")
    Classification classification;
    @Column(name = "blazonPath")
    String blazonPath;

    public Integer getBlazonId() {
        return blazonId;
    }

    public void setBlazonId(Integer blazonId) {
        this.blazonId = blazonId;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public String getBlazonPath() {
        return blazonPath;
    }

    public void setBlazonPath(String blazonPath) {
        this.blazonPath = blazonPath;
    }
}
