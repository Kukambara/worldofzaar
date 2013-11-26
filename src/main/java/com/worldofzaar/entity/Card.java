package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Cards\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Card {
    @Id
    @SequenceGenerator(name = "card_seq", sequenceName = "\"Cards_cardId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq")
    @Column(name = "\"cardId\"")
    private Integer cardId;
    @Column(name = "\"cardEnergy\"")
    private Integer cardEnergy;
    @Column(name = "\"cardPicture\"")
    private String cardPicture;
    @ManyToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "\"propertyId\"")
    private Property property;
    @ManyToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "\"cardClassId\"")
    private Classification classification;
    @Column(name = "\"isElite\"")
    private Boolean isElite;
    @Column(name = "\"propertySystemString\"")
    private String propertySystemString;
    @ManyToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "\"subsetId\"")
    private Subset subset;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getCardEnergy() {
        return cardEnergy;
    }

    public void setCardEnergy(Integer cardEnergy) {
        this.cardEnergy = cardEnergy;
    }

    public String getCardPicture() {
        return cardPicture;
    }

    public void setCardPicture(String cardPicture) {
        this.cardPicture = cardPicture;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Boolean getElite() {
        return isElite;
    }

    public void setElite(Boolean elite) {
        isElite = elite;
    }

    public String getPropertySystemString() {
        return propertySystemString;
    }

    public void setPropertySystemString(String propertySystemString) {
        this.propertySystemString = propertySystemString;
    }

    public Subset getSubset() {
        return subset;
    }

    public void setSubset(Subset subset) {
        this.subset = subset;
    }
}
