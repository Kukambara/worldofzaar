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
@Table(name = "\"Blazons\"")
public class Blazon {
    @Id
    @SequenceGenerator(name = "blazon_seq", sequenceName = "\"Blazons_blazonId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blazon_seq")
    @Column(name = "\"blazonId\"")
    private Integer blazonId;
    @ManyToOne
    @JoinColumn(name = "\"classId\"")
    private Classification classification;
    @Column(name = "\"blazonPath\"")
    private String blazonPath;
    @ManyToOne
    @JoinColumn(name = "\"clothId\"")
    private Cloth cloth;

    public Cloth getCloth() {
        return cloth;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

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
