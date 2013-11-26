package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Properties\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Property {
    @Id
    @SequenceGenerator(name = "property_seq", sequenceName = "\"Properties_propertyId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_seq")
    @Column(name = "\"propertyId\"")
    private Integer propertyId;
    @Column(name = "\"propertyRealization\"")
    private String propertyRealization;
    @Column(name = "\"propertySystemDescription\"")
    private String propertySystemDescription;

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyRealization() {
        return propertyRealization;
    }

    public void setPropertyRealization(String propertyRealization) {
        this.propertyRealization = propertyRealization;
    }

    public String getPropertySystemDescription() {
        return propertySystemDescription;
    }

    public void setPropertySystemDescription(String propertySystemDescription) {
        this.propertySystemDescription = propertySystemDescription;
    }

/*
    *
    * Method 1
    *
    * Class<?> clazz = Class.forName("java.util.Date");
    * Object date = clazz.newInstance();
    *
    * Method 2
    * Class<?> clazz = Class.forName("com.foo.MyClass");
    * Constructor<?> constructor = clazz.getConstructor(String.class, Integer.class);
    * Object instance = constructor.newInstance("stringparam", 42);
    *
    */

}
