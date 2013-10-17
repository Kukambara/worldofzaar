package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"SetTexts\"")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SetText {
    @Id
    @SequenceGenerator(name = "setText_seq", sequenceName = "\"SetTexts_setTextId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "setText_seq")
    @Column(name = "\"setTextId\"")
    private Integer setTextId;
    @Column(name = "\"setName\"")
    private String setName;
    @Column(name = "\"setInfo\"")
    private String setInfo;
    @ManyToOne
    @JoinColumn(name = "\"setId\"")
    private Set set;

    public Integer getSetTextId() {
        return setTextId;
    }

    public void setSetTextId(Integer setTextId) {
        this.setTextId = setTextId;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetInfo() {
        return setInfo;
    }

    public void setSetInfo(String setInfo) {
        this.setInfo = setInfo;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }
}
