package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 18:04
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"EngPropertyTexts\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AttributeOverrides({
        @AttributeOverride(name = "propertyInfo", column = @Column(name = "\"propertyInfo\""))
})
@AssociationOverrides({
        @AssociationOverride(name = "supportCard", joinColumns = @JoinColumn(name = "\"supportCardId\"")),
        @AssociationOverride(name = "warriorCard", joinColumns = @JoinColumn(name = "\"warriorCardId\""))
})
public class EngPropertyText extends PropertyText {
}
