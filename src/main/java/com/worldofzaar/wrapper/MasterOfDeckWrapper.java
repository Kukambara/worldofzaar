package com.worldofzaar.wrapper;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.11.13
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class MasterOfDeckWrapper {
    private int masterId;
    private int cardId;
    private String cardName;
    private String raceName;
    private String className;
    private int level;
    private int price;
    private int donatePrice;

    public MasterOfDeckWrapper(Object[] object) {
        try {
            this.masterId = (Integer) object[0];
            this.className = (String) object[1];
            this.raceName = (String) object[2];
            if (object[3] != null)
                this.cardId = (Integer) object[3];
            if (object[4] != null)
                this.cardId = (Integer) object[4];
            this.cardName = (String) object[5];
            this.level = (Integer) object[6];
            this.price = (Integer) object[7];
            this.donatePrice = (Integer) object[8];
        } catch (ClassCastException e) {
            System.out.println("MasterOfDeckWrapper(Object[])Class cast exception: " + e.getCause());
        }
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDonatePrice() {
        return donatePrice;
    }

    public void setDonatePrice(int donatePrice) {
        this.donatePrice = donatePrice;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
