package com.worldofzaar.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
public class PropertyText {

    Integer propertyTextId;
    Property property;
    String propertyInfo;

    public Integer getPropertyTextId() {
        return propertyTextId;
    }

    public void setPropertyTextId(Integer propertyTextId) {
        this.propertyTextId = propertyTextId;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getPropertyInfo() {
        return propertyInfo;
    }

    public void setPropertyInfo(String propertyInfo) {
        this.propertyInfo = propertyInfo;
    }
}
