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
@Table(name = "\"Requests\"")
public class Request {
    @Id
    @GeneratedValue
    @Column(name = "\"requestId\"")
    private Integer requestId;
    @ManyToOne
    @JoinColumn(name = "\"userId\"")
    private User user;
    @Column(name = "\"countOfUsers\"")
    private Integer countOfUsers;

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
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
