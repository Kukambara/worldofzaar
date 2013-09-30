package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "CardTexts")
public class CardText {
    @Id
    @GeneratedValue
    @Column(name = "cardTextId")
    Integer cardTextId;
    @ManyToOne
    @JoinColumn(name = "warriorCardId")
    WarriorCard warriorCard;
    @ManyToOne
    @JoinColumn(name = "supportCardId")
    SupportCard supportCard;
    @Column(name = "cardName")
    String cardName;
    @Column(name = "cardSlogan")
    String cardSlogan;

    public Integer getCardTextId() {
        return cardTextId;
    }

    public void setCardTextId(Integer cardTextId) {
        this.cardTextId = cardTextId;
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

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardSlogan() {
        return cardSlogan;
    }

    public void setCardSlogan(String cardSlogan) {
        this.cardSlogan = cardSlogan;
    }
}
