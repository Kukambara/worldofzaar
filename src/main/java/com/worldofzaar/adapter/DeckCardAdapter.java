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

    private Integer deckCardId;
    private Integer deckId;
    private Integer cardId;


    public DeckCardAdapter(DeckCard inputDeckCard){

        deckCardId = inputDeckCard.getDeckCardId();
        deckId = inputDeckCard.getDeck().getDeckId();
        if(inputDeckCard.getWarriorCard() != null){
            cardId = inputDeckCard.getWarriorCard().getCardId();
        }else{
            cardId = inputDeckCard.getSupportCard().getCardId();
        }
    }

    public Integer getDeckCardId() {
        return deckCardId;
    }

    public void setDeckCardId(Integer deckCardId) {
        this.deckCardId = deckCardId;
    }

    public Integer getDeckId() {
        return deckId;
    }

    public void setDeckId(Integer deckId) {
        this.deckId = deckId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}
