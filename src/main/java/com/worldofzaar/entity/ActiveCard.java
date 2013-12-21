package com.worldofzaar.entity;

import com.worldofzaar.entity.enums.Location;
import com.worldofzaar.service.ActiveCardService;
import com.worldofzaar.service.SupportCardService;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 09.10.13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"ActiveCards\"")
public class ActiveCard {
    @Id
    @GeneratedValue
    @Column(name = "\"activeCardId\"")
    private Integer activeCardId;
    @OneToOne
    @JoinColumn(name = "\"heroId\"")
    private Hero hero;
    @OneToOne
    @JoinColumn(name = "\"warriorCardId\"")
    private ActiveWarriorCard activeWarriorCard;
    @Transient
    private SupportCard supportCard;
    @Column(name = "\"supportCardId\"")
    private Integer supportCardId;
    @Column(name = "\"position\"")
    private Integer position;
    @Column(name = "\"location\"")
    @Type(type = "com.worldofzaar.entity.enums.PGEnumUserType",
            parameters = @org.hibernate.annotations.Parameter(name = "enumClassName", value = "com.worldofzaar.entity.enums.Location"))
    private Location location;

    public Integer getActiveCardId() {
        return activeCardId;
    }

    public void setActiveCardId(Integer activeDeckId) {
        this.activeCardId = activeDeckId;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public SupportCard getSupportCard() {
        if (supportCard == null) {
            SupportCardService supportCardService = new SupportCardService();
            supportCard = supportCardService.getCard(supportCardId);
        }
        return supportCard;
    }

    public void setSupportCard(SupportCard supportCard) {
        supportCardId = supportCard.getCardId();
        this.supportCard = supportCard;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public ActiveWarriorCard getActiveWarriorCard() {
        return activeWarriorCard;
    }

    public void setActiveWarriorCard(ActiveWarriorCard activeWarriorCard) {
        this.activeWarriorCard = activeWarriorCard;
    }

    public Integer getSupportCardId() {
        return supportCardId;
    }

    public void setSupportCardId(Integer supportCardId) {
        this.supportCardId = supportCardId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean attackCard(ActiveCard card) {
        if (card == null)
            return false;
        boolean isAttack = getActiveWarriorCard().attackCard(card.getActiveWarriorCard());
        //Throw card off into talon if card has died and update this active card.
        if (getActiveWarriorCard().getCurrentHealth() <= 0) {
            location = Location.talon;
            ActiveCardService activeCardService = new ActiveCardService();
            activeCardService.updateCard(this);
        }
        return isAttack;
    }

    public int getEnergyOfThrow() {
        int energy = 0;
        if (activeWarriorCard != null)
            energy = activeWarriorCard.getWarriorCard().getCardEnergy();

        if (supportCard != null)
            energy = supportCard.getCardEnergy();

        return (energy + (2 + 1)) / 2;
    }
}
