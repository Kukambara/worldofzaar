package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.11.13
 * Time: 13:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Discounts\"")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Discount {
    @Id
    @SequenceGenerator(name = "discount_seq", sequenceName = "\"Discounts_discountId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_seq")
    @Column(name = "\"discountId\"")
    private Integer discountId;
    @ManyToOne
    @JoinColumn(name = "\"classId\"")
    private Classification classification;
    @ManyToOne
    @JoinColumn(name = "\"supportCardId\"")
    private SupportCard supportCard;
    @ManyToOne
    @JoinColumn(name = "\"warriorCardId\"")
    private WarriorCard warriorCard;

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public SupportCard getSupportCard() {
        return supportCard;
    }

    public void setSupportCard(SupportCard supportCard) {
        this.supportCard = supportCard;
    }

    public WarriorCard getWarriorCard() {
        return warriorCard;
    }

    public void setWarriorCard(WarriorCard warriorCard) {
        this.warriorCard = warriorCard;
    }
}
