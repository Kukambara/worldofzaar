package com.worldofzaar.service;

import com.worldofzaar.dao.HeroDao;
import com.worldofzaar.entity.Game;
import com.worldofzaar.entity.Hero;
import com.worldofzaar.entity.HeroCard;
import com.worldofzaar.entity.User;
import com.worldofzaar.util.UserInformation;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class HeroService {
    public Hero createHero(User user) {
        return createHero(user, false);
    }

    public Hero getHero(UserInformation userInformation) {
        HeroDao heroDao = new HeroDao();
        return heroDao.getHero(userInformation);
    }

    public Hero createHero(User user, boolean isActive) {
        HeroCard heroCard = createHeroCard();
        HeroDao heroDao = new HeroDao();
        Hero hero = new Hero();
        hero.setUser(user);
        hero.setHealth(30);
        hero.setEnergy(6);
        hero.setMysteryPower(1);
        hero.setArmor(0);
        hero.setActive(isActive);
        hero.setNegativeEffect(0);
        hero.setHeroCard(heroCard);
        heroDao.add(hero);

        //Create hero's active cards
        createActiveCards(hero);
        return hero;
    }

    //Create hero's table cards. (3 warrior and 1 support)
    public HeroCard createHeroCard() {
        HeroCardService heroCardService = new HeroCardService();
        return heroCardService.createHeroCard();
    }

    public void createActiveCards(Hero hero) {
        ActiveCardService activeCardService = new ActiveCardService();
        activeCardService.createActiveCards(hero);
    }

    public void heroIsReady(HttpServletRequest request) {
        UserInformation userInformation = new UserInformation(request);
        HeroDao heroDao = new HeroDao();
        heroDao.heroIsReady(userInformation);
        GameService gameService = new GameService();
        Game game = gameService.getGame(request);
        gameService.gameIsReady(game, request);
    }

    public void setActiveHero(Hero hero) {
        HeroDao heroDao = new HeroDao();
        heroDao.heroIsActive(hero);
    }

    public void setUnActiveHero(Hero hero) {
        HeroDao heroDao = new HeroDao();
        heroDao.heroIsUnActive(hero);
    }

    public void updateHero(Hero hero) {
        HeroDao heroDao = new HeroDao();
        heroDao.update(hero);
    }
}
