package com.worldofzaar.adapter;

import com.worldofzaar.entity.ActiveCard;
import com.worldofzaar.entity.Game;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 04.12.13
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class GameAdapter {
    private List<ActiveCard> userCardsInHand;
    private Game game;
    private Integer heroId;

    public List<ActiveCard> getUserCardsInHand() {
        return userCardsInHand;
    }

    public void setUserCardsInHand(List<ActiveCard> userCardsInHand) {
        this.userCardsInHand = userCardsInHand;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getHeroId() {
        return heroId;
    }

    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
    }
}
