package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"PropertyTexts\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PropertyText {
    @Id
    @SequenceGenerator(name = "propertyText_seq", sequenceName = "\"PropertyTexts_propertyTextId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "propertyText_seq")
    @Column(name = "\"propertyTextId\"")
    private Integer propertyTextId;
    @ManyToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "\"supportCardId\"")
    private SupportCard supportCard;
    @ManyToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "\"warriorCardId\"")
    private WarriorCard warriorCard;

    @Column(name = "\"propertyInfo\"")
    private String propertyInfo;

    public Integer getPropertyTextId() {
        return propertyTextId;
    }

    public void setPropertyTextId(Integer propertyTextId) {
        this.propertyTextId = propertyTextId;
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

    public String getPropertyInfo() {
        return propertyInfo;
    }

    public void setPropertyInfo(String propertyInfo) {
        this.propertyInfo = propertyInfo;
    }
}
