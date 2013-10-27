package com.worldofzaar.adapter;

import com.worldofzaar.entity.UserCard;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class UserCardAdapter {

    private int userCardId;
    private int userId;
    private int warriorCardId;
    private int supportCardId;

    public UserCardAdapter(UserCard inputUserCard) {
       userCardId = inputUserCard.getUserCardId();
       userId = inputUserCard.getUser().getUserId();
       warriorCardId = inputUserCard.getWarriorCard().getCardId();
       supportCardId = inputUserCard.getSupportCard().getCardId();
    }

    public int getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(int userCardId) {
        this.userCardId = userCardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
