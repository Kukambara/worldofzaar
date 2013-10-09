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
    @GeneratedValue
    @Column(name = "\"propertyId\"")
    private Integer propertyId;

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }
}
