package com.worldofzaar.service;

import com.worldofzaar.dao.HeroDao;
import com.worldofzaar.entity.Hero;
import com.worldofzaar.entity.User;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class HeroService {
    public void createHero(User user) {
        HeroDao heroDao = new HeroDao();
        Hero hero = new Hero();
        hero.setUser(user);
        hero.setHealth(30);
        hero.setEnergy(6);
        hero.setMysteryPower(1);
        hero.setArmor(0);
        hero.setActive(false);
        hero.setNegativeEffect(0);


    }

}
