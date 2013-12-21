package com.worldofzaar.entity;

import com.worldofzaar.service.UserService;

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
    private final int HERO_ARMOR = 2;
    @Id
    @GeneratedValue
    @Column(name = "\"heroId\"")
    private Integer heroId;
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
    @Column(name = "\"isReady\"")
    private Boolean isReady;

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
        if (user == null) {
            UserService userService = new UserService();
            user = userService.getUser(userId);
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null)
            this.userId = user.getUserId();
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

    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }

    public boolean attackHero(int attack) {
        if (health == null)
            return false;

        int currentArmor = (armor >= HERO_ARMOR) ? HERO_ARMOR : armor;
        int damage = attack - currentArmor;
        health = health - damage;

        return true;
    }
}
