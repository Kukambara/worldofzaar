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

    public int getDeckCardId() {
        return deckCardId;
    }

    public void setDeckCardId(int deckCardId) {
        this.deckCardId = deckCardId;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public int getWarriorCardId() {
        return warriorCardId;
    }

    public void setWarriorCardId(int warriorCardId) {
        this.warriorCardId = warriorCardId;
    }

    public int getSupportCardId() {
        return supportCardId;
    }

    public void setSupportCardId(int supportCardId) {
        this.supportCardId = supportCardId;
    }

    private int warriorCardId;
    private int supportCardId;

    public DeckCardAdapter(DeckCard inputDeckCard){

        deckCardId = inputDeckCard.getDeckCardId();
        deckId = inputDeckCard.getDeck().getDeckId();
        if(inputDeckCard.getWarriorCard() != null){
            warriorCardId = inputDeckCard.getWarriorCard().getCardId();
        }else{
            supportCardId = inputDeckCard.getSupportCard().getCardId();
        }
    }
}
