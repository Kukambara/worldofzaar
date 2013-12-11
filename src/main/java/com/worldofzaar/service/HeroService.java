package com.worldofzaar.service;

import com.worldofzaar.dao.HeroDao;
import com.worldofzaar.entity.Hero;
import com.worldofzaar.entity.HeroCard;
import com.worldofzaar.entity.User;

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
        return hero;
    }

    //Create hero's table cards. (3 warrior and 1 support)
    public HeroCard createHeroCard() {
        HeroCardService heroCardService = new HeroCardService();
        return heroCardService.createHeroCard();
    }

}
