package com.worldofzaar.adapter;

import com.worldofzaar.entity.WarriorCard;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 18:37
 * To change this template use File | Settings | File Templates.
 */
public class WarriorCardAdapter extends CardAdapter {

    private Integer cardArmor;
    private Integer cardDamage;
    private Integer cardHealth;


    public WarriorCardAdapter(Object[] input) {
        super(input);
        this.cardArmor = ((WarriorCard)input[0]).getCardArmor();
        this.cardDamage =((WarriorCard)input[0]).getCardDamage();
        this.cardHealth =((WarriorCard)input[0]).getCardHealth();
    }

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
