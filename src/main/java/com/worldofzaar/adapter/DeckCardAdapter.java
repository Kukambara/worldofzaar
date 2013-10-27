package com.worldofzaar.adapter;

import com.worldofzaar.entity.DeckCard;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
public class DeckCardAdapter {

    private int deckCardId;
    private int deckId;
    private int warriorCardId;
    private int supportCardId;

    public DeckCardAdapter(DeckCard inputDeckCard){

        deckCardId = inputDeckCard.getDeckCardId();
        deckId = inputDeckCard.getDeck().getDeckId();
        warriorCardId = inputDeckCard.getWarriorCard().getCardId();
        supportCardId = inputDeckCard.getSupportCard().getCardId();
    }
}
