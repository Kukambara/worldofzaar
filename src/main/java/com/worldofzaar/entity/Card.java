package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Cards\"")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Card {
    @Id
    @GeneratedValue
    @Column(name = "\"cardId\"")
    private Integer cardId;
    @Column(name = "\"cardEnergy\"")
    private Integer cardEnergy;
    @Column(name = "\"cardPicture\"")
    private String cardPicture;
    @ManyToOne
    @JoinColumn(name = "\"propertyId\"")
    private Property property;
    @ManyToOne
    @JoinColumn(name = "\"cardRaceId\"")
    private Race race;
    @ManyToOne
    @JoinColumn(name = "\"cardClassId\"")
    private Classification classification;
    @Column(name = "\"isElite\"")
    private Boolean isElite;
    @Column(name = "\"propertySystemString\"")
    private String propertySystemString;
    @Column(name = "\"cardLevel\"")
    private Integer cardLevel;
    @ManyToOne
    @JoinColumn(name = "\"setId\"")
    private Set set;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        cardId = cardId;
    }

    public Integer getCardEnergy() {
        return cardEnergy;
    }

    public void setCardEnergy(Integer cardEnergy) {
        cardEnergy = cardEnergy;
    }

    public String getCardPicture() {
        return cardPicture;
    }

    public void setCardPicture(String cardPicture) {
        cardPicture = cardPicture;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Boolean getElite() {
        return isElite;
    }

    public void setElite(Boolean elite) {
        isElite = elite;
    }

    public String getPropertySystemString() {
        return propertySystemString;
    }

    public void setPropertySystemString(String propertySystemString) {
        this.propertySystemString = propertySystemString;
    }

    public Integer getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(Integer cardLevel) {
        this.cardLevel = cardLevel;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }
}
