package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 09.10.13
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"HeroCards\"")
public class HeroCards {
    @Id
    @GeneratedValue
    @Column(name = "\"heroCardsId\"")
    private Integer heroCardsId;
    @ManyToOne
    @JoinColumn(name = "\"firstActiveCurrentCardId\"")
    private ActiveWarriorCard firstActiveWarriorCard;
    @ManyToOne
    @JoinColumn(name = "\"secondActiveCurrentCardId\"")
    private ActiveWarriorCard secondActiveWarriorCard;
    @ManyToOne
    @JoinColumn(name = "\"thirdActiveCurrentCardId\"")
    private ActiveWarriorCard thirdActiveWarriorCard;
    //TODO Link object and Id
    @Transient
    private SupportCard supportCard;
    @Column(name = "\"supportCardId\"")
    private Integer supportCardId;

    public Integer getHeroCardsId() {
        return heroCardsId;
    }

    public void setHeroCardsId(Integer heroCardsId) {
        this.heroCardsId = heroCardsId;
    }

    public ActiveWarriorCard getFirstActiveWarriorCard() {
        return firstActiveWarriorCard;
    }

    public void setFirstActiveWarriorCard(ActiveWarriorCard firstActiveWarriorCard) {
        this.firstActiveWarriorCard = firstActiveWarriorCard;
    }

    public ActiveWarriorCard getSecondActiveWarriorCard() {
        return secondActiveWarriorCard;
    }

    public void setSecondActiveWarriorCard(ActiveWarriorCard secondActiveWarriorCard) {
        this.secondActiveWarriorCard = secondActiveWarriorCard;
    }

    public ActiveWarriorCard getThirdActiveWarriorCard() {
        return thirdActiveWarriorCard;
    }

    public void setThirdActiveWarriorCard(ActiveWarriorCard thirdActiveWarriorCard) {
        this.thirdActiveWarriorCard = thirdActiveWarriorCard;
    }

    public SupportCard getSupportCard() {
        return supportCard;
    }

    public void setSupportCard(SupportCard supportCard) {
        this.supportCard = supportCard;
    }

    public Integer getSupportCardId() {
        return supportCardId;
    }

    public void setSupportCardId(Integer supportCardId) {
        supportCardId = supportCardId;
    }
}
