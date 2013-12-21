package com.worldofzaar.adapter;

import com.worldofzaar.entity.*;

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
    private Subset subset;

    private String cardName;
    private String propertyInfo;
    private String cardSlogan;

    public CardAdapter(Object[] input) {

        this.cardId = ((Card)input[0]).getCardId();
        this.cardEnergy = ((Card)input[0]).getCardEnergy();
        this.cardPicture =((Card)input[0]).getCardPicture();
        this.propertyId =((Card)input[0]).getProperty().getPropertyId();
        this.classificationId =((Card)input[0]).getClassification().getClassificationId();
        this.isElite =((Card)input[0]).getElite();
        this.subset = ((Card)input[0]).getSubset();
        this.cardName =((CardText)input[1]).getCardName();
        this.cardSlogan =((CardText)input[1]).getCardSlogan();
        this.propertyInfo = ((PropertyText)input[2]).getPropertyInfo();
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Subset getSubset() {
        return subset;
    }

    public void setSubset(Subset subset) {
        this.subset = subset;
    }

    public Boolean getIsElite() {
        return isElite;
    }

    public void setIsElite(Boolean isElite) {
        this.isElite = isElite;
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
