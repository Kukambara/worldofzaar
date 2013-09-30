package com.worldofzaar.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 */
public class MasterOfDeck {

    Integer mastersCardId;
    Integer cardLevel;
    WarriorCard warriorCard;
    SupportCard supportCard;
    Integer price;
    Integer donatePrice;

    public Integer getMastersCardId() {
        return mastersCardId;
    }

    public void setMastersCardId(Integer mastersCardId) {
        this.mastersCardId = mastersCardId;
    }

    public Integer getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(Integer cardLevel) {
        this.cardLevel = cardLevel;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDonatePrice() {
        return donatePrice;
    }

    public void setDonatePrice(Integer donatePrice) {
        this.donatePrice = donatePrice;
    }
}
