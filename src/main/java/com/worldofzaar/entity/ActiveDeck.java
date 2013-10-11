package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 09.10.13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"ActiveDecks\"")
public class ActiveDeck {
    @Id
    @GeneratedValue
    @Column(name = "\"activeDeckId\"")
    private Integer activeDeckId;
    @ManyToOne
    @JoinColumn(name = "\"heroId\"")
    private Hero hero;
    //TODO Link object and Id
    @Transient
    private WarriorCard warriorCard;
    @Column(name = "\"warriorCardId\"")
    private Integer warriorCardId;
    @Transient
    private SupportCard supportCard;
    @Column(name = "\"supportCardId\"")
    private Integer supportCardId;
    @Column(name = "\"isDeck\"")
    private Boolean isDeck;
    @Column(name = "\"isTalon\"")
    private Boolean isTalon;
    @Column(name = "\"isHand\"")
    private Boolean isHand;
    @Column(name = "\"position\"")
    private Integer position;

    public Integer getActiveDeckId() {
        return activeDeckId;
    }

    public void setActiveDeckId(Integer activeDeckId) {
        this.activeDeckId = activeDeckId;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public WarriorCard getWarriorCard() {
        return warriorCard;
    }

    public void setWarriorCard(WarriorCard warriorCard) {
        this.warriorCard = warriorCard;
    }

    public SupportCard getSupportCard() {
        return supportCard;
    }

    public void setSupportCard(SupportCard supportCard) {
        this.supportCard = supportCard;
    }

    public Boolean getDeck() {
        return isDeck;
    }

    public void setDeck(Boolean deck) {
        isDeck = deck;
    }

    public Boolean getTalon() {
        return isTalon;
    }

    public void setTalon(Boolean talon) {
        isTalon = talon;
    }

    public Boolean getHand() {
        return isHand;
    }

    public void setHand(Boolean hand) {
        isHand = hand;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getWarriorCardId() {
        return warriorCardId;
    }

    public void setWarriorCardId(Integer warriorCardId) {
        this.warriorCardId = warriorCardId;
    }

    public Integer getSupportCardId() {
        return supportCardId;
    }

    public void setSupportCardId(Integer supportCardId) {
        this.supportCardId = supportCardId;
    }
}
