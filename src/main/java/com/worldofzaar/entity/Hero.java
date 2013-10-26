package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 09.10.13
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Heroes\"")
public class Hero {
    @Id
    @GeneratedValue
    @Column(name = "\"heroId\"")
    private Integer heroId;
    //TODO Link object and Id
    @Transient
    private User user;
    @Column(name = "\"userId\"")
    private Integer userId;
    @OneToOne
    @JoinColumn(name = "\"heroCardId\"")
    private HeroCard heroCard;
    @Column(name = "\"health\"")
    private Integer health;
    @Column(name = "\"energy\"")
    private Integer energy;
    @Column(name = "\"armor\"")
    private Integer armor;
    @Column(name = "\"mysteryPower\"")
    private Integer mysteryPower;
    @Column(name = "\"negativeEffect\"")
    private Integer negativeEffect;
    @Column(name = "\"isActive\"")
    private Boolean isActive;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHeroId() {
        return heroId;
    }

    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HeroCard getHeroCard() {
        return heroCard;
    }

    public void setHeroCard(HeroCard heroCard) {
        this.heroCard = heroCard;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getMysteryPower() {
        return mysteryPower;
    }

    public void setMysteryPower(Integer mysteryPower) {
        this.mysteryPower = mysteryPower;
    }

    public Integer getNegativeEffect() {
        return negativeEffect;
    }

    public void setNegativeEffect(Integer negativeEffect) {
        this.negativeEffect = negativeEffect;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
