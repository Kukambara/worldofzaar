package com.worldofzaar.entity;

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
public class User {
    @Id
    @GeneratedValue
    @Column(name = "\"userId\"")
    private Integer userId;
    @Column(name = "\"userName\"")
    private String userName;
    @Column(name = "\"userLogin\"")
    private String userLogin;
    @Column(name = "\"userEmail\"")
    private String userEmail;
    @Column(name = "\"userPassword\"")
    private String userPassword;
    @ManyToOne
    @JoinColumn(name = "\"gameProfileId\"")
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public GameProfile getGameProfile() {
        return gameProfile;
    }

    public void setGameProfile(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
    }
}
