package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Decks\"")
public class Deck {
    @Id
    @GeneratedValue
    @Column(name = "\"deckId\"")
    private Integer deckId;
    @ManyToOne
    @JoinColumn(name = "\"userId\"")
    private User user;
    @Column(name = "\"deckName\"")
    private String deckName;

    public Integer getDeckId() {
        return deckId;
    }

    public void setDeckId(Integer deckId) {
        this.deckId = deckId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }
}
