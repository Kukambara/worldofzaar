package com.worldofzaar.modelAttribute;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 30.11.13
 * Time: 9:32
 * To change this template use File | Settings | File Templates.
 */
public class ApiTable implements Validation {
    private int cost;
    private int size;
    private int position;
    private int level;

    public ApiTable() {
        cost = -1;
        size = -1;
        position = -1;
        level = -1;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean valid() {
        return (cost == -1 || size == -1 || position == -1 || level == -1) ? false : true;
    }
}
