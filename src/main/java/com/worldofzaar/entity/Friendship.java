package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Friendships")
public class Friendship {
    @Id
    @GeneratedValue
    @Column(name = "friendshipId")
    Integer friendshipId;
    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
    @ManyToOne
    @JoinColumn(name = "friendId")
    User friendId;

    public Integer getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Integer friendshipId) {
        this.friendshipId = friendshipId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriendId() {
        return friendId;
    }

    public void setFriendId(User friendId) {
        this.friendId = friendId;
    }
}
