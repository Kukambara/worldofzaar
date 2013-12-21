package com.worldofzaar.service;

import com.worldofzaar.adapter.GameAdapter;
import com.worldofzaar.util.UserInformation;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 04.12.13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class GameAdapterService {

    public GameAdapter getGameAdapter(HttpServletRequest request) {
        UserInformation userInformation = new UserInformation(request);
        GameService gameService = new GameService();
        HeroService heroService = new HeroService();
        ActiveCardService activeCardService = new ActiveCardService();

        GameAdapter gameAdapter = new GameAdapter();

        gameAdapter.setGame(gameService.getGame(request));
        gameAdapter.setUserCardsInHand(activeCardService.getUserActiveCardsInHand(userInformation.getUserId()));
        gameAdapter.setHeroId(heroService.getHero(userInformation).getHeroId());
        return gameAdapter;
    }
}
