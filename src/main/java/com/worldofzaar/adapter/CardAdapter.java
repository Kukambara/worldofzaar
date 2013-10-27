package com.worldofzaar.adapter;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public class CardAdapter {

    private Integer cardId;
    private String cardName;
    private String cardSlogan;
    private Integer cardEnergy;
    private String cardPicture;
    private String property;
    private Integer classificationId;
    private Boolean isElite;
    private Integer setId;




    public CardAdapter(Object[] input) {
        this.cardId = (Integer)input[0];
        this.cardName =(String) input[2];
        this.cardSlogan =(String) input[5];
        this.cardEnergy = (Integer)input[3];
        this.cardPicture =(String) input[7];
        this.property =(String) input[6];
        this.classificationId =(Integer) input[1];
        isElite =(Boolean) input[8];
        this.setId = (Integer)input[4];


    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardSlogan() {
        return cardSlogan;
    }

    public void setCardSlogan(String cardSlogan) {
        this.cardSlogan = cardSlogan;
    }

    public Integer getCardEnergy() {
        return cardEnergy;
    }

    public void setCardEnergy(Integer cardEnergy) {
        this.cardEnergy = cardEnergy;
    }

    public String getCardPicture() {
        return cardPicture;
    }

    public void setCardPicture(String cardPicture) {
        this.cardPicture = cardPicture;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }

    public Boolean getElite() {
        return isElite;
    }

    public void setElite(Boolean elite) {
        isElite = elite;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

}
