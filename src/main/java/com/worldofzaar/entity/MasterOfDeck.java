package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"MasterOfDeck\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MasterOfDeck {

    @Id
    @SequenceGenerator(name = "masterOfDeck_seq", sequenceName = "\"MasterOfDeck_mastersCard_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "masterOfDeck_seq")
    @Column(name = "\"mastersCard\"")
    private Integer mastersCardId;
    @Column(name = "\"cardLevel\"")
    private Integer cardLevel;
    @OneToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "\"warriorCardId\"")
    private WarriorCard warriorCard;
    @OneToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "\"supportCardId\"")
    private SupportCard supportCard;
    @Column(name = "\"price\"")
    private Integer price;
    @Column(name = "\"donatePrice\"")
    private Integer donatePrice;

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
