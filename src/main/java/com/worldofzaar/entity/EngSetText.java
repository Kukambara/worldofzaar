package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"EngSetTexts\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AttributeOverrides({
        @AttributeOverride(name = "setName", column = @Column(name = "\"setName\"")),
        @AttributeOverride(name = "setInfo", column = @Column(name = "\"setInfo\""))
})
@AssociationOverrides({
        @AssociationOverride(name = "set", joinColumns = @JoinColumn(name = "\"setId\""))
})
public class EngSetText extends SetText {
}
