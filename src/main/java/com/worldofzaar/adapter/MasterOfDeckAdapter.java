package com.worldofzaar.adapter;

import com.worldofzaar.entity.MasterOfDeck;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
public class MasterOfDeckAdapter {
    private Integer mastersCardId;
    private Integer cardLevel;
    private WarriorCardAdapter warriorCard;
    private SupportCardAdapter supportCard;
    private Integer price;
    private Integer donatePrice;

    public MasterOfDeckAdapter(MasterOfDeck inputMasterOfDeck) {
        this.mastersCardId = inputMasterOfDeck.getMastersCardId();
        this.cardLevel = inputMasterOfDeck.getCardLevel();
        this.price = inputMasterOfDeck.getPrice();
        this.donatePrice = inputMasterOfDeck.getDonatePrice();

    }

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

    public WarriorCardAdapter getWarriorCard() {
        return warriorCard;
    }

    public void setWarriorCard(WarriorCardAdapter warriorCard) {
        this.warriorCard = warriorCard;
    }

    public SupportCardAdapter getSupportCard() {
        return supportCard;
    }

    public void setSupportCard(SupportCardAdapter supportCard) {
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
