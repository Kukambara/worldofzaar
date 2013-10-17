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
    @SequenceGenerator(name = "coalitionRequest_seq", sequenceName = "\"CoalitionRequests_coalitionRequestId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coalitionRequest_seq")
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
