package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"EngClassTexts\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AttributeOverrides({
        @AttributeOverride(name = "className", column = @Column(name = "\"className\"")),
        @AttributeOverride(name = "classDescription", column = @Column(name = "\"classDescription\"")),
        @AttributeOverride(name = "classPictureNamePath", column = @Column(name = "\"classPictureNamePath\""))
})
@AssociationOverrides({
        @AssociationOverride(name = "classification", joinColumns = @JoinColumn(name = "\"classId\""))
})
public class EngClassText extends ClassText {
}
