package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"EngRaceTexts\"")
@AttributeOverrides({
        @AttributeOverride(name = "raceName", column = @Column(name = "\"raceName\"")),
        @AttributeOverride(name = "raceDescription", column = @Column(name = "\"raceDescription\""))
})
@AssociationOverrides({
        @AssociationOverride(name = "race", joinColumns = @JoinColumn(name = "\"raceId\""))
})
public class EngRaceText extends RaceText {
}
