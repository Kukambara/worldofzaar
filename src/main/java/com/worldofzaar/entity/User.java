package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:07
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Users\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "\"Users_userId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "\"userId\"")
    private Integer userId;
    @Column(name = "\"userName\"")
    private String userName;
    @OneToOne
    @JoinColumn(name = "\"gameProfileId\"")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private GameProfile gameProfile;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public GameProfile getGameProfile() {
        return gameProfile;
    }

    public void setGameProfile(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
    }
}
