package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Subsets\"")
public class Subset {
    @Id
    @SequenceGenerator(name = "subset_seq", sequenceName = "\"Sets_setId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subset_seq")
    @Column(name = "\"subsetId\"")
    private Integer subsetId;
    @Column(name = "\"frontPath\"")
    private String frontPath;
    @ManyToOne
    @JoinColumn(name = "\"setId\"")
    private Set set;
    @Column(name = "\"subsetTechName\"")
    private String subsetTechName;

    public Integer getSubsetId() {
        return subsetId;
    }

    public void setSubsetId(Integer subsetId) {
        this.subsetId = subsetId;
    }

    public String getFrontPath() {
        return frontPath;
    }

    public void setFrontPath(String frontPath) {
        this.frontPath = frontPath;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public String getSubsetTechName() {
        return subsetTechName;
    }

    public void setSubsetTechName(String subsetTechName) {
        this.subsetTechName = subsetTechName;
    }
}
