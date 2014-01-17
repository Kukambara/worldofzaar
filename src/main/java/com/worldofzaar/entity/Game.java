package com.worldofzaar.entity;

import com.worldofzaar.entity.enums.Activity;
import com.worldofzaar.entity.enums.Phase;
import com.worldofzaar.entity.enums.State;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

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
    @SequenceGenerator(name = "game_seq", sequenceName = "\"Games_gameId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_seq")
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
    private Boolean isReady;
    @OneToOne
    @JoinColumn(name = "\"chatId\"")
    private Chat chat;
    @OneToOne
    @JoinColumn(name = "\"logId\"")
    private Log log;
    @Column(name = "\"state\"")
    @Type(type = "com.worldofzaar.entity.enums.PGEnumUserType",
            parameters = @Parameter(name = "enumClassName", value = "com.worldofzaar.entity.enums.State"))
    private State state;
    @Column(name = "\"phase\"")
    @Type(type = "com.worldofzaar.entity.enums.PGEnumUserType",
            parameters = @Parameter(name = "enumClassName", value = "com.worldofzaar.entity.enums.Phase"))
    private Phase phase;
    @Column(name = "\"step\"")
    private Integer step;
    @Column(name = "\"indexOfActiveHero\"")
    private Integer IndexOfActiveHero;
    @Column(name = "\"firstHeroIndex\"")
    private Integer firstCircleHeroIndex;
    @Column(name = "\"moveDuration\"")
    private Integer moveDuration;


    public Integer getIndexOfActiveHero() {
        return IndexOfActiveHero;
    }

    public void setIndexOfActiveHero(Integer indexOfActiveHero) {
        IndexOfActiveHero = indexOfActiveHero;
    }

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

    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getFirstCircleHeroIndex() {
        return firstCircleHeroIndex;
    }

    public void setFirstCircleHeroIndex(Integer firstCircleHeroIndex) {
        this.firstCircleHeroIndex = firstCircleHeroIndex;
    }

    public Integer getMoveDuration() {
        return moveDuration;
    }

    public void setMoveDuration(Integer moveDuration) {
        this.moveDuration = moveDuration;
    }

    public Hero[] getAllHeroes() {
        return new Hero[]{getFirstHero(), getSecondHero(), getThirdHero(), getFourthHero()};
    }

    public Hero getHeroFromPosition(int position) {
        switch (position) {
            case 0:
                return firstHero;
            case 1:
                return secondHero;
            case 2:
                return thirdHero;
            case 3:
                return fourthHero;
            default:
                return null;
        }
    }

    public int getPositionByHero(Hero hero) {
        if (hero.equals(firstHero))
            return 0;
        else if (hero.equals(secondHero))
            return 1;
        else if (hero.equals(thirdHero))
            return 2;
        else if (hero.equals(fourthHero))
            return 3;
        return -1;
    }

    public void increasePhase() {
        if (phase == Phase.ACTIVE) {
            phase = Phase.WAR;
            return;
        } else if (phase == Phase.WAR) {
            phase = Phase.END;
            return;
        } else if (phase == Phase.END) {
            phase = Phase.ACTIVE;
            return;
        }

    }

    public Hero getNextActiveHero() {
        Hero[] heroes = getAllHeroes();
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i] != null) {
                if (heroes[i].getActivity() == Activity.ACTIVE) {
                    return getNextHero(i);
                }
            }
        }
        return null;
    }

    public Hero getActiveHero() {
        Hero[] heroes = getAllHeroes();
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i] != null) {
                if (heroes[i].getActivity() == Activity.ACTIVE) {
                    return heroes[i];
                }
            }
        }
        return null;
    }

    public Hero getNextHero(int index) {
        Hero[] heroes = getAllHeroes();
        int loop = 1;
        for (int i = index; i < heroes.length; i++) {
            if (loop == 2 && i == index) {
                return heroes[i];
            }
            if (i != index && heroes[i] != null) {
                return heroes[i];
            }
            if (i + 1 == heroes.length) {
                i = -1;
                loop++;
            }
        }
        return null;
    }
}
