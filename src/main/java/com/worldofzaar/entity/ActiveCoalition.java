package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 09.10.13
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"ActiveCoalitions\"")
public class ActiveCoalition {
    @Id
    @GeneratedValue
    @Column(name = "\"coalitionId\"")
    private Integer coalitionId;
    @OneToOne
    @JoinColumn(name = "\"firstHeroId\"")
    private Hero firstHero;
    @OneToOne
    @JoinColumn(name = "\"secondHeroId\"")
    private Hero secondHero;
    @OneToOne
    @JoinColumn(name = "\"gameId\"")
    private Game game;

    public Integer getCoalitionId() {
        return coalitionId;
    }

    public void setCoalitionId(Integer coalitionId) {
        this.coalitionId = coalitionId;
    }

    public Hero getFirstHero() {
        return firstHero;
    }

    public void setFirstHero(Hero firstHero) {
        this.firstHero = firstHero;
    }

    public Hero getSecondHero() {
        return secondHero;
    }

    public void setSecondHero(Hero secondHero) {
        this.secondHero = secondHero;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
