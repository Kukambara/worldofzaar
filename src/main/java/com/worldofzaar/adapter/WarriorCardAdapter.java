package com.worldofzaar.adapter;

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
        this.cardArmor = (Integer)input[12-1];
        this.cardDamage =(Integer) input[13-1];
        this.cardHealth =(Integer) input[11-1];
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
