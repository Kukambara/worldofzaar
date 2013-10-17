package com.worldofzaar.entity;

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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PropertyText {
    @Id
    @SequenceGenerator(name = "propertyText_seq", sequenceName = "\"PropertyTexts_propertyTextId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "propertyText_seq")
    @Column(name = "\"propertyTextId\"")
    private Integer propertyTextId;
    @ManyToOne
    @JoinColumn(name = "\"propertyId\"")
    private Property property;
    @Column(name = "\"propertyInfo\"")
    private String propertyInfo;

    public Integer getPropertyTextId() {
        return propertyTextId;
    }

    public void setPropertyTextId(Integer propertyTextId) {
        this.propertyTextId = propertyTextId;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getPropertyInfo() {
        return propertyInfo;
    }

    public void setPropertyInfo(String propertyInfo) {
        this.propertyInfo = propertyInfo;
    }
}
