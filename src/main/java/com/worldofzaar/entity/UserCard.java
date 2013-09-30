package com.worldofzaar.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public class UserCard {

    Integer UserCardId;
    User userId;
    WarriorCard warriorCard;
    SupportCard supportCard;

    public Integer getUserCardId() {
        return UserCardId;
    }

    public void setUserCardId(Integer userCardId) {
        UserCardId = userCardId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public WarriorCard getWarriorCard() {
        return warriorCard;
    }

    public void setWarriorCard(WarriorCard warriorCard) {
        this.warriorCard = warriorCard;
    }

    public SupportCard getSupportCard() {
        return supportCard;
    }

    public void setSupportCard(SupportCard supportCard) {
        this.supportCard = supportCard;
    }
}
