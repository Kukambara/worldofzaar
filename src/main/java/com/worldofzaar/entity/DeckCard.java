package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"DecksCards\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DeckCard {
    @Id
    @SequenceGenerator(name = "deckCard_seq", sequenceName = "\"DecksCards_decksCardId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deckCard_seq")
    @Column(name = "\"decksCardId\"")
    private Integer deckCardId;
    @ManyToOne
    @JoinColumn(name = "\"deckId\"")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Deck deck;
    @ManyToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "\"warriorCardId\"")
    private WarriorCard warriorCard;
    @ManyToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "\"supportCardId\"")
    private SupportCard supportCard;

    public Integer getDeckCardId() {
        return deckCardId;
    }

    public void setDeckCardId(Integer deckCardId) {
        this.deckCardId = deckCardId;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public WarriorCard getWarriorCard() {
        return warriorCard;
    }

    public void setWarriorCard(WarriorCard warriorCard) {
        this.warriorCard = warriorCard;
    }

    public SupportCard getSupportCard() {
        return supportCard;
    }

    public void setSupportCard(SupportCard supportCard) {
        this.supportCard = supportCard;
    }
}
