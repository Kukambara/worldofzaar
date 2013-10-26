package com.worldofzaar.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import org.hibernate.annotations.Parameter;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 09.10.13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Games\"")
public class Game {
    @Id
    @GeneratedValue
    @Column(name = "\"gameId\"")
    private Integer gameId;
    @OneToOne
    @JoinColumn(name = "\"firstHeroId\"")
    private Hero firstHero;
    @OneToOne
    @JoinColumn(name = "\"secondHeroId\"")
    private Hero secondHero;
    @OneToOne
    @JoinColumn(name = "\"thirdHeroId\"")
    private Hero thirdHero;
    @OneToOne
    @JoinColumn(name = "\"fourthHeroId\"")
    private Hero fourthHero;
    @Column(name = "\"bank\"")
    private Integer bank;
    @Column(name = "\"isPrivate\"")
    private Boolean isPrivate;
    @OneToOne
    @JoinColumn(name = "\"chatId\"")
    private Chat chat;
    @OneToOne
    @JoinColumn(name = "\"logId\"")
    private Log log;
    @Column(name = "\"phase\"")
    @Type(type = "com.worldofzaar.entity.PGEnumUserType",
            parameters = @Parameter(name = "enumClassName", value = "com.worldofzaar.entity.Phase"))
    private Phase phase;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
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

    public Hero getThirdHero() {
        return thirdHero;
    }

    public void setThirdHero(Hero thirdHero) {
        this.thirdHero = thirdHero;
    }

    public Hero getFourthHero() {
        return fourthHero;
    }

    public void setFourthHero(Hero fourthHero) {
        this.fourthHero = fourthHero;
    }

    public Integer getBank() {
        return bank;
    }

    public void setBank(Integer bank) {
        this.bank = bank;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
}
