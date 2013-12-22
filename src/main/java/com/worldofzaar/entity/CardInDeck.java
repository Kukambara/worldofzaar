package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;


/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 02.12.13
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"CardsInDeck\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CardInDeck {
    @Id
    @SequenceGenerator(name = "cardInDeck_seq", sequenceName = "\"CardsInDeck_cardsInDeckId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardInDeck_seq")
    @Column(name = "\"cardsInDeckId\"")
    private Integer cardId;
    @ManyToOne
    @JoinColumn(name = "\"deckId\"")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Deck deck;
    @ManyToOne
    @JoinColumn(name = "\"userCardId\"")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private UserCard card;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public UserCard getCard() {
        return card;
    }

    public void setCard(UserCard card) {
        this.card = card;
    }
}
