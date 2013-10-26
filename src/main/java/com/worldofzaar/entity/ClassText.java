package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"ClassTexts\"")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ClassText {
    @Id
    @SequenceGenerator(name = "ClassText_seq", sequenceName = "\"ClassTexts_classTextId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClassText_seq")
    @Column(name = "\"classTextId\"")
    private Integer classTextId;
    @OneToOne
    @JoinColumn(name = "\"classId\"")
    private Classification classification;
    @Column(name = "\"className\"")
    private String className;
    @Column(name = "\"classDescription\"")
    private String classDescription;

    public Integer getClassTextId() {
        return classTextId;
    }

    public void setClassTextId(Integer classTextId) {
        this.classTextId = classTextId;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }
}
