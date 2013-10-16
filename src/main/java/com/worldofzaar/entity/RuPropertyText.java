package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"RuPropertyTexts\"")
@AttributeOverrides({
        @AttributeOverride(name = "propertyInfo", column = @Column(name = "\"propertyInfo\""))
})
@AssociationOverrides({
        @AssociationOverride(name = "property", joinColumns = @JoinColumn(name = "\"propertyId\""))
})
public class RuPropertyText extends PropertyText {
}
