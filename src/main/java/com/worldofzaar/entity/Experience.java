package com.worldofzaar.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Experiences\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Experience {
    @Id
    @SequenceGenerator(name = "experience_seq", sequenceName = "\"Experiences_experienceId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experience_seq")
    @Column(name = "\"experienceId\"")
    private Integer experienceId;
    @Column(name = "\"level\"")
    private Integer level;
    @Column(name = "\"levelExperience\"")
    private Integer levelExperience;

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
