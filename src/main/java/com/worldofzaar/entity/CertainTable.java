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
@Table(name = "\"CertainTables\"")
public class CertainTable {
    @Id
    @SequenceGenerator(name = "request_seq", sequenceName = "\"Requests_requestId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq")
    @Column(name = "\"requestId\"")
    private Integer requestId;
    @OneToOne
    @JoinColumn(name = "\"userId\"")
    private User user;
    @Column(name = "\"tableSize\"")
    private Integer tableSize;
    @Column(name = "\"seatPosition\"")
    private Integer seatPosition;
    @Column(name = "\"tableCost\"")
    private Integer tableCost;
    @Column(name = "\"level\"")
    private Integer level;

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

    public Integer getTableSize() {
        return tableSize;
    }

    public void setTableSize(Integer tableSize) {
        this.tableSize = tableSize;
    }

    public Integer getSeatPosition() {
        return seatPosition;
    }

    public void setSeatPosition(Integer seatPosition) {
        this.seatPosition = seatPosition;
    }

    public Integer getTableCost() {
        return tableCost;
    }

    public void setTableCost(Integer tableCost) {
        this.tableCost = tableCost;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
