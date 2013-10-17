package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 08.10.13
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Coalitions\"")
public class Coalition {
    @Id
    @SequenceGenerator(name = "coalition_seq", sequenceName = "\"Coalitions_coalitionId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coalition_seq")
    @Column(name = "\"coalitionId\"")
    private Integer coalitionId;
    @ManyToOne
    @JoinColumn(name = "\"firstPlayerId\"")
    private User firstPlayer;
    @ManyToOne
    @JoinColumn(name = "\"secondPlayerId\"")
    private User secondPlayer;

    public Integer getCoalitionId() {
        return coalitionId;
    }

    public void setCoalitionId(Integer coalitionId) {
        this.coalitionId = coalitionId;
    }

    public User getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(User firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public User getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(User secondPlayer) {
        this.secondPlayer = secondPlayer;
    }
}
