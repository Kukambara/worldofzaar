package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "DeckCards")
public class DeckCard {
    @Id
    @GeneratedValue
    @Column(name = "deckCardId")
    Integer deckCardId;
    @ManyToOne
    @JoinColumn(name = "deckId")
    Deck deck;
    @ManyToOne
    @JoinColumn(name = "warriorCardId")
    WarriorCard warriorCard;
    @ManyToOne
    @JoinColumn(name = "supportCardId")
    SupportCard supportCard;

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
