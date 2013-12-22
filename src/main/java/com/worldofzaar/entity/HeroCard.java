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
public class HeroCard {
    @Id
    @GeneratedValue
    @Column(name = "\"heroCardsId\"")
    private Integer heroCardsId;
    @OneToOne
    @JoinColumn(name = "\"firstActiveCurrentCardId\"")
    private ActiveCard firstActiveWarriorCard;
    @OneToOne
    @JoinColumn(name = "\"secondActiveCurrentCardId\"")
    private ActiveCard secondActiveWarriorCard;
    @OneToOne
    @JoinColumn(name = "\"thirdActiveCurrentCardId\"")
    private ActiveCard thirdActiveWarriorCard;
    @OneToOne
    @JoinColumn(name = "\"activeSupportCardId\"")
    private ActiveCard supportCard;

    public Integer getHeroCardsId() {
        return heroCardsId;
    }

    public void setHeroCardsId(Integer heroCardsId) {
        this.heroCardsId = heroCardsId;
    }

    public ActiveCard getFirstActiveWarriorCard() {
        return firstActiveWarriorCard;
    }

    public void setFirstActiveWarriorCard(ActiveCard firstActiveWarriorCard) {
        this.firstActiveWarriorCard = firstActiveWarriorCard;
    }

    public ActiveCard getSecondActiveWarriorCard() {
        return secondActiveWarriorCard;
    }

    public void setSecondActiveWarriorCard(ActiveCard secondActiveWarriorCard) {
        this.secondActiveWarriorCard = secondActiveWarriorCard;
    }

    public ActiveCard getThirdActiveWarriorCard() {
        return thirdActiveWarriorCard;
    }

    public void setThirdActiveWarriorCard(ActiveCard thirdActiveWarriorCard) {
        this.thirdActiveWarriorCard = thirdActiveWarriorCard;
    }

    public ActiveCard getSupportCard() {
        return supportCard;
    }

    public void setSupportCard(ActiveCard supportCard) {
        this.supportCard = supportCard;
    }

    public ActiveCard getCard(Integer cardId) {
        if (supportCard != null && supportCard.getActiveCardId().equals(cardId))
            return supportCard;
        if (firstActiveWarriorCard != null && firstActiveWarriorCard.getActiveCardId().equals(cardId))
            return firstActiveWarriorCard;
        if (secondActiveWarriorCard != null && secondActiveWarriorCard.getActiveCardId().equals(cardId))
            return secondActiveWarriorCard;
        if (thirdActiveWarriorCard != null && thirdActiveWarriorCard.getActiveCardId().equals(cardId))
            return thirdActiveWarriorCard;
        return null;
    }
}
