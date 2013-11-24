package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"WarriorCards\"")
@AttributeOverrides({
        @AttributeOverride(name = "cardEnergy", column = @Column(name = "\"cardEnergy\"")),
        @AttributeOverride(name = "cardPicture", column = @Column(name = "\"cardPicture\"")),
        @AttributeOverride(name = "isElite", column = @Column(name = "\"isElite\"")),
        @AttributeOverride(name = "propertySystemString", column = @Column(name = "\"propertySystemString\""))
})
@AssociationOverrides({
        @AssociationOverride(name = "property", joinColumns = @JoinColumn(name = "\"propertyId\"")),
        @AssociationOverride(name = "classification", joinColumns = @JoinColumn(name = "\"cardClassId\"")),
        @AssociationOverride(name = "subset", joinColumns = @JoinColumn(name = "\"subsetId\""))
})
public class WarriorCard extends Card {
    @Column(name = "\"cardArmor\"")
    private Integer cardArmor;
    @Column(name = "\"cardDamage\"")
    private Integer cardDamage;
    @Column(name = "\"cardHealth\"")
    private Integer cardHealth;

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
