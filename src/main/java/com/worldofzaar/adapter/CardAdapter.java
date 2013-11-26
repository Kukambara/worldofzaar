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
    private Integer cardEnergy;
    private String cardPicture;
    private Integer propertyId;
    private Integer classificationId;
    private Boolean isElite;
    //private String propertySystemString;
    private Integer subsetId;
    private String subsetImage;
    private String cardName;
    private String propertyInfo;
    private String cardSlogan;

    public CardAdapter(Object[] input) {

        this.cardId = (Integer)input[0];
        this.cardEnergy = (Integer)input[1];
        this.cardPicture =(String) input[2];
        this.propertyId =(Integer) input[3];
        this.classificationId =(Integer) input[4];
        this.isElite =(Boolean) input[5];
        this.subsetId = (Integer)input[6];
        this.subsetImage = (String)input[7];
        this.cardName =(String) input[8];
        this.cardSlogan =(String) input[9];
        this.propertyInfo ="" ;// (String) input[10];
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getSubsetId() {
        return subsetId;
    }

    public void setSubsetId(Integer subsetId) {
        this.subsetId = subsetId;
    }

    public String getSubsetImage() {
        return subsetImage;
    }

    public void setSubsetImage(String subsetImage) {
        this.subsetImage = subsetImage;
    }

    public String getPropertyInfo() {
        return propertyInfo;
    }

    public void setPropertyInfo(String propertyInfo) {
        this.propertyInfo = propertyInfo;
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


}
