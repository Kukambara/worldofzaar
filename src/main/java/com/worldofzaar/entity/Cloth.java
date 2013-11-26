package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 14.10.13
 * Time: 10:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Clothes\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Cloth {
    @Id
    @SequenceGenerator(name = "cloth_seq", sequenceName = "\"Clothes_clothId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cloth_seq")
    @Column(name = "\"clothId\"")
    private Integer clothId;
    @Column(name = "\"clothPath\"")
    private String clothPath;

    public Integer getClothId() {
        return clothId;
    }

    public void setClothId(Integer clothId) {
        this.clothId = clothId;
    }

    public String getClothPath() {
        return clothPath;
    }

    public void setClothPath(String clothPath) {
        this.clothPath = clothPath;
    }
}
