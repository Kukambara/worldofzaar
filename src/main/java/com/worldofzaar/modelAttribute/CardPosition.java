package com.worldofzaar.modelAttribute;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 06.12.13
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public class CardPosition implements Validation{

    private int cardId;
    private int position;

    public CardPosition() {
        cardId = -1;
        position = -1;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean valid() {
        if (cardId == -1 || !Positions.consist(position))
            return false;
        return true;
    }

    public enum Positions {
        SUPPORT(0),
        FIRST(1),
        SECOND(2),
        THIRD(3);
        private int position;

        Positions(int position) {
            this.position = position;
        }

        public static boolean consist(int value) {
            for (Positions p : Positions.values()) {
                if (p.getValue() == value)
                    return true;
            }
            return false;
        }

        public int getValue() {
            return position;
        }
    }
}
