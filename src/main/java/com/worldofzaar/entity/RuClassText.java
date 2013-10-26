package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"RuClassTexts\"")
@AttributeOverrides({
        @AttributeOverride(name = "className", column = @Column(name = "\"className\"")),
        @AttributeOverride(name = "classDescription", column = @Column(name = "\"classDescription\""))
})
@AssociationOverrides({
        @AssociationOverride(name = "classification", joinColumns = @JoinColumn(name = "\"classId\""))
})
public class RuClassText extends ClassText {
}
