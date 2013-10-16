package com.worldofzaar.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Sets\"")
public class Set {
    @Id
    @GeneratedValue
    @Column(name = "\"setId\"")
    private Integer setId;
    @Column(name = "\"date\"")
    private Date date;

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
