package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Experiences")
public class Experience {
    @Id
    @GeneratedValue
    @Column(name = "experienceId")
    Integer experienceId;
    @Column(name = "level")
    Integer level;
    @Column(name = "levelExperience")
    Integer levelExperience;

    public Integer getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Integer experienceId) {
        this.experienceId = experienceId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevelExperience() {
        return levelExperience;
    }

    public void setLevelExperience(Integer levelExperience) {
        this.levelExperience = levelExperience;
    }
}
