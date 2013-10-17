package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 08.10.13
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"MyGames\"")
public class MyGame {
    @Id
    @SequenceGenerator(name = "myGame_seq", sequenceName = "\"MyGames_myGameId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myGame_seq")
    @Column(name = "\"myGameId\"")
    private Integer myGameId;
    @ManyToOne
    @JoinColumn(name = "\"userId\"")
    private User user;
    @Column(name = "\"countOfUsers\"")
    private Integer countOfUsers;

    public Integer getMyGameId() {
        return myGameId;
    }

    public void setMyGameId(Integer myGameId) {
        this.myGameId = myGameId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCountOfUsers() {
        return countOfUsers;
    }

    public void setCountOfUsers(Integer countOfUsers) {
        this.countOfUsers = countOfUsers;
    }
}
