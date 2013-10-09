package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"RuCardTexts\"")
@AttributeOverrides({
        @AttributeOverride(name = "cardName", column = @Column(name = "\"cardName\"")),
        @AttributeOverride(name = "cardSlogan", column = @Column(name = "\"cardSlogan\""))
})
@AssociationOverrides({
        @AssociationOverride(name = "warriorCard", joinColumns = @JoinColumn(name = "\"warriorCardId\"")),
        @AssociationOverride(name = "supportCard", joinColumns = @JoinColumn(name = "\"supportCardId\""))
})
public class RuCardText extends CardText {

}
