package com.worldofzaar.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 13.11.13
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Set\"")
public class Set {
    @Id
    @SequenceGenerator(name = "set_seq", sequenceName = "\"Set_setId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "set_seq")
    @Column(name = "\"setId\"")
    private Integer setId;
    @Column(name = "\"date\"")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }
}
