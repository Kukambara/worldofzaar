package com.worldofzaar.modelAttribute;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 21.12.13
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class Card implements Validation {
    private int cardId;

    public Card() {
        cardId = -1;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public boolean valid() {
        return (cardId == -1) ? false : true;
    }
}
