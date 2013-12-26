package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 25.12.13
 * Time: 11:01
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"VkUsers\"")
public class VkUser {
    @Id
    @Column(name = "\"viewerId\"")
    private Integer viewerId;
    @OneToOne
    @JoinColumn(name = "\"userId\"")
    private User user;

    public Integer getViewerId() {
        return viewerId;
    }

    public void setViewerId(Integer viewerId) {
        this.viewerId = viewerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
