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
@Table(name = "\"CoalitionRequests\"")
public class CoalitionRequest {
    @Id
    @GeneratedValue
    @Column(name = "\"coalitionRequestId\"")
    private Integer coalitionRequestId;
    @ManyToOne
    @JoinColumn(name = "\"coalitionId\"")
    private Coalition coalition;

    public Integer getCoalitionRequestId() {
        return coalitionRequestId;
    }

    public void setCoalitionRequestId(Integer coalitionRequestId) {
        this.coalitionRequestId = coalitionRequestId;
    }

    public Coalition getCoalition() {
        return coalition;
    }

    public void setCoalition(Coalition coalition) {
        this.coalition = coalition;
    }
}
