package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 08.10.13
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Notifications\"")
public class Notification {
    @Id
    @GeneratedValue
    @Column(name = "\"notificationId\"")
    private Integer notificationId;
    @ManyToOne
    @JoinColumn(name = "\"myGameId\"")
    private MyGame myGame;
    @ManyToOne
    @JoinColumn(name = "\"userId\"")
    private User user;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public MyGame getMyGame() {
        return myGame;
    }

    public void setMyGame(MyGame myGame) {
        this.myGame = myGame;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
