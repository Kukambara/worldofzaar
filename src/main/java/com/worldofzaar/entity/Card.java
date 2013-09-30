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
@Table(name = "Cards")
public class Card {
    @Id
    @GeneratedValue
    @Column(name = "cardId")
    Integer CardId;
    @Column(name = "cardEnergy")
    Integer CardEnergy;
    @Column(name = "cardPicture")
    String CardPicture;
    @ManyToOne
    @JoinColumn(name = "propertyId")
    Property property;
    @ManyToOne
    @JoinColumn(name = "cardRaceId")
    Race race;
    @ManyToOne
    @JoinColumn(name = "cardClassId")
    Classification classification;
    @Column(name = "isElite")
    Boolean isElite;
    @Column(name = "propertySystemString")
    String propertySystemString;
    @Column(name = "cardLevel")
    Integer cardLevel;
    @ManyToOne
    @JoinColumn(name = "setId")
    Set set;

    public Integer getCardId() {
        return CardId;
    }

    public void setCardId(Integer cardId) {
        CardId = cardId;
    }

    public Integer getCardEnergy() {
        return CardEnergy;
    }

    public void setCardEnergy(Integer cardEnergy) {
        CardEnergy = cardEnergy;
    }

    public String getCardPicture() {
        return CardPicture;
    }

    public void setCardPicture(String cardPicture) {
        CardPicture = cardPicture;
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
