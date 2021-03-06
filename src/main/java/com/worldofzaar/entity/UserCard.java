package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"UserCards\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserCard {
    @Id
    @SequenceGenerator(name = "userCard_seq", sequenceName = "\"UserCards_userCardId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userCard_seq")
    @Column(name = "\"userCardId\"")
    private Integer userCardId;
    @ManyToOne
    @JoinColumn(name = "\"userId\"")
    private User user;
    @OneToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "\"warriorCardId\"")
    private WarriorCard warriorCard;
    @OneToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "\"supportCardId\"")
    private SupportCard supportCard;

    public Integer getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(Integer userCardId) {
        this.userCardId = userCardId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WarriorCard getWarriorCard() {
        return warriorCard;
    }

    public void setWarriorCard(WarriorCard warriorCard) {
        this.warriorCard = warriorCard;
    }

    public SupportCard getSupportCard() {
        return supportCard;
    }

    public void setSupportCard(SupportCard supportCard) {
        this.supportCard = supportCard;
    }
}
