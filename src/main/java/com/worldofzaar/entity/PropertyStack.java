package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"PropertiesStacks\"")
public class PropertyStack {
    @Id
    @SequenceGenerator(name = "propertyStack_seq", sequenceName = "\"PropertiesStacks_stackId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "propertyStack_seq")
    @Column(name = "\"stackId\"")
    private Integer stackId;
    @ManyToOne
    @JoinColumn(name = "\"heroId\"")
    private Hero hero;
    //TODO Link object and Id
    @Transient
    private Property property;
    @Column(name = "\"propertyId\"")
    private Integer propertyId;
    @Column(name = "\"currentSystemString\"")
    private String currentSystemString;
    @Column(name = "\"stepCount\"")
    private Integer stepCount;

    public Integer getStackId() {
        return stackId;
    }

    public void setStackId(Integer stackId) {
        this.stackId = stackId;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getCurrentSystemString() {
        return currentSystemString;
    }

    public void setCurrentSystemString(String currentSystemString) {
        this.currentSystemString = currentSystemString;
    }

    public Integer getStepCount() {
        return stepCount;
    }

    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }
}
