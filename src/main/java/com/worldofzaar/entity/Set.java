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
    @SequenceGenerator(name = "set_seq", sequenceName = "\"Sets_setId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "set_seq")
    @Column(name = "\"setId\"")
    private Integer setId;
    @Column(name = "\"frontPath\"")
    private String frontPath;
    @Column(name = "\"date\"")
    private Date date;

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public String getFrontPath() {
        return frontPath;
    }

    public void setFrontPath(String frontPath) {
        this.frontPath = frontPath;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
