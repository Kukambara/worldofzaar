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
@Table(name = "\"Friendships\"")
public class Friendship {
    @Id
    @SequenceGenerator(name = "friendship_seq", sequenceName = "\"Friendships_friendshipId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friendship_seq")
    @Column(name = "\"friendshipId\"")
    private Integer friendshipId;
    @ManyToOne
    @JoinColumn(name = "\"userId\"")
    private User user;
    @OneToOne
    @JoinColumn(name = "\"friendId\"")
    private User friendId;

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
