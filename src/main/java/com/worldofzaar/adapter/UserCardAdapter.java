package com.worldofzaar.adapter;

import com.worldofzaar.entity.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class UserCardAdapter {

    private int userCardId;
    private WarriorCard warriorCard;
    private SupportCard supportCard;
    private String cardName;
    private String cardProperty;
    private String cardSlogan;


    public UserCardAdapter(Object[] inputUserCard) {
        userCardId = ((UserCard)inputUserCard[0]).getUserCardId();
        if (((UserCard)inputUserCard[0]).getWarriorCard() != null) {
            warriorCard = ((UserCard)inputUserCard[0]).getWarriorCard();
        } else {
            supportCard = ((UserCard)inputUserCard[0]).getSupportCard();
        }
        cardName = ((RuCardText)inputUserCard[1]).getCardName();
        cardSlogan = ((RuCardText)inputUserCard[1]).getCardSlogan();
        cardProperty = ((RuPropertyText)inputUserCard[2]).getPropertyInfo();
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardProperty() {
        return cardProperty;
    }

    public void setCardProperty(String cardProperty) {
        this.cardProperty = cardProperty;
    }

    public String getCardSlogan() {
        return cardSlogan;
    }

    public void setCardSlogan(String cardSlogan) {
        this.cardSlogan = cardSlogan;
    }

    public int getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(int userCardId) {
        this.userCardId = userCardId;
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
}