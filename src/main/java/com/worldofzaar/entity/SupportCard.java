package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"SupportCards\"")
@AttributeOverrides({
        @AttributeOverride(name = "cardEnergy", column = @Column(name = "\"cardEnergy\"")),
        @AttributeOverride(name = "cardPicture", column = @Column(name = "\"cardPicture\"")),
        @AttributeOverride(name = "isElite", column = @Column(name = "\"isElite\"")),
        @AttributeOverride(name = "propertySystemString", column = @Column(name = "\"propertySystemString\""))
})
@AssociationOverrides({
        @AssociationOverride(name = "property", joinColumns = @JoinColumn(name = "\"propertyId\"")),
        @AssociationOverride(name = "classification", joinColumns = @JoinColumn(name = "\"cardClassId\"")),
        @AssociationOverride(name = "set", joinColumns = @JoinColumn(name = "\"setId\""))
})
public class SupportCard extends Card {

}
