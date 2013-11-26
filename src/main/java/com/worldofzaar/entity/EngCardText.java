package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"EngCardTexts\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AttributeOverrides({
        @AttributeOverride(name = "cardName", column = @Column(name = "\"cardName\"")),
        @AttributeOverride(name = "cardSlogan", column = @Column(name = "\"cardSlogan\""))
})
@AssociationOverrides({
        @AssociationOverride(name = "warriorCard", joinColumns = @JoinColumn(name = "\"warriorCardId\"")),
        @AssociationOverride(name = "supportCard", joinColumns = @JoinColumn(name = "\"supportCardId\""))
})
public class EngCardText extends CardText {

}
