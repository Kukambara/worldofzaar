package com.worldofzaar.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
public class WarriorCard extends Card {

    Integer cardArmor;
    Integer cardDamage;
    Integer cardHealth;

    public Integer getCardArmor() {
        return cardArmor;
    }

    public void setCardArmor(Integer cardArmor) {
        this.cardArmor = cardArmor;
    }

    public Integer getCardDamage() {
        return cardDamage;
    }

    public void setCardDamage(Integer cardDamage) {
        this.cardDamage = cardDamage;
    }

    public Integer getCardHealth() {
        return cardHealth;
    }

    public void setCardHealth(Integer cardHealth) {
        this.cardHealth = cardHealth;
    }
}
