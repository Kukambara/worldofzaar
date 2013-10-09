package com.worldofzaar.entity;

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
public class UserCard {
    @Id
    @GeneratedValue
    @Column(name = "\"userCardId\"")
    private Integer userCardId;
    @ManyToOne
    @JoinColumn(name = "\"userId\"")
    private User user;
    @ManyToOne
    @JoinColumn(name = "\"warriorCardId\"")
    private WarriorCard warriorCard;
    @ManyToOne
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
