package com.worldofzaar.adapter;

import com.worldofzaar.entity.Deck;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class DeckAdapter {

    private int deckId;
    private String deckName;
    private int userId;

    public DeckAdapter(Deck deck){

        deckId = deck.getDeckId();
        deckName = deck.getDeckName();
        userId  = deck.getUser().getUserId();
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
