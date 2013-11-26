package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Deck {
    @Id
    @SequenceGenerator(name = "deck_seq", sequenceName = "\"Decks_deckId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deck_seq")
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
