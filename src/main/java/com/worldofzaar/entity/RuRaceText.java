package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"RuRaceTexts\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AttributeOverrides({
        @AttributeOverride(name = "raceName", column = @Column(name = "\"raceName\"")),
        @AttributeOverride(name = "raceDescription", column = @Column(name = "\"raceDescription\"")),
        @AttributeOverride(name = "raceNamePicturePath", column = @Column(name = "\"raceNamePicturePath\""))
})
@AssociationOverrides({
        @AssociationOverride(name = "race", joinColumns = @JoinColumn(name = "\"raceId\""))
})
public class RuRaceText extends RaceText {
}
