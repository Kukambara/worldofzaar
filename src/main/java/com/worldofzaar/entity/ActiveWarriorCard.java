package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 11.10.13
 * Time: 12:10
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"WarriorCurrentCards\"")
public class ActiveWarriorCard {
    @Id
    @GeneratedValue
    @Column(name = "\"warriorCurrentCardId\"")
    private Integer activeWarriorCardId;
    //TODO Link object and Id
    @Transient
    private WarriorCard warriorCard;
    @Column(name = "\"warriorCardId\"")
    private Integer warriorCardId;
    @Column(name = "\"currentHealth\"")
    private Integer currentHealth;
    @Column(name = "\"currentArmor\"")
    private Integer currentArmor;
    @Column(name = "\"currentAttack\"")
    private Integer currentAttack;
    @Column(name = "\"stepCount\"")
    private Integer stepCount;

    public Integer getActiveWarriorCardId() {
        return activeWarriorCardId;
    }

    public void setActiveWarriorCardId(Integer activeWarriorCardId) {
        this.activeWarriorCardId = activeWarriorCardId;
    }

    public WarriorCard getWarriorCard() {
        return warriorCard;
    }

    public void setWarriorCard(WarriorCard warriorCard) {
        this.warriorCard = warriorCard;
    }

    public Integer getWarriorCardId() {
        return warriorCardId;
    }

    public void setWarriorCardId(Integer warriorCardId) {
        this.warriorCardId = warriorCardId;
    }

    public Integer getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(Integer currentHealth) {
        this.currentHealth = currentHealth;
    }

    public Integer getCurrentArmor() {
        return currentArmor;
    }

    public void setCurrentArmor(Integer currentArmor) {
        this.currentArmor = currentArmor;
    }

    public Integer getCurrentAttack() {
        return currentAttack;
    }

    public void setCurrentAttack(Integer currentAttack) {
        this.currentAttack = currentAttack;
    }

    public Integer getStepCount() {
        return stepCount;
    }

    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }
}
