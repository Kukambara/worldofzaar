package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Properties\"")
public class Property {
    @Id
    @SequenceGenerator(name = "property_seq", sequenceName = "\"Properties_propertyId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_seq")
    @Column(name = "\"propertyId\"")
    private Integer propertyId;

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }
}
