package com.worldofzaar.modelAttribute;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 06.12.13
 * Time: 11:43
 * To change this template use File | Settings | File Templates.
 */
public class Move implements Validation{
    //Карта которой бьют
    private int myCardId;
    //Карта которую бьют
    private int enemyCardId;
    //Игрок которого бьют
    private int enemyId;

    public Move() {
        myCardId = -1;
        enemyCardId = -1;
        enemyId = -1;
    }

    public boolean valid() {
        if (myCardId == -1 || enemyId == -1)
            return false;
        return true;
    }

    public int getMyCardId() {
        return myCardId;
    }

    public void setMyCardId(int myCardId) {
        this.myCardId = myCardId;
    }

    public int getEnemyCardId() {
        return enemyCardId;
    }

    public void setEnemyCardId(int enemyCardId) {
        this.enemyCardId = enemyCardId;
    }

    public int getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(int enemyId) {
        this.enemyId = enemyId;
    }

    public boolean isCardAttack() {
        return (enemyCardId == -1) ? false : true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MOVE: ").append("myCardId = ").append(myCardId).append("; enemyCardId = ")
                .append(enemyCardId).append("; enemyId = ").append(enemyId).append(";");
        return sb.toString();
    }
}
