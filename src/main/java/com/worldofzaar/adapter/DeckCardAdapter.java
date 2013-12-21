package com.worldofzaar.adapter;

import com.worldofzaar.entity.CardInDeck;
import com.worldofzaar.entity.DeckCard;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
public class DeckCardAdapter {

    private Integer cardInDeckId;
    private Integer deckId;
    private Integer cardId;
    private Integer userCardId;
    private Boolean isWarrior;

    public DeckCardAdapter(CardInDeck cardInDeck){

        cardInDeckId = cardInDeck.getCardId();
        deckId = cardInDeck.getDeck().getDeckId();
        if(cardInDeck.getCard().getWarriorCard() != null){
            cardId = cardInDeck.getCard().getWarriorCard().getCardId();
            isWarrior =true;
        }else{
            cardId = cardInDeck.getCard().getSupportCard().getCardId();
            isWarrior =false;
        }
        userCardId = cardInDeck.getCard().getUserCardId();
    }

    public Integer getCardInDeckId() {
        return cardInDeckId;
    }

    public void setCardInDeckId(Integer cardInDeckId) {
        this.cardInDeckId = cardInDeckId;
    }

    public Integer getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(Integer userCardId) {
        this.userCardId = userCardId;
    }

    public Boolean getIsWarrior() {
        return isWarrior;
    }

    public void setIsWarrior(Boolean isWarrior) {
        this.isWarrior = isWarrior;
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
