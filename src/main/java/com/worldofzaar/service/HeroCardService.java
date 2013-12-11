package com.worldofzaar.service;

import com.worldofzaar.dao.HeroCardDao;
import com.worldofzaar.entity.HeroCard;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class HeroCardService {
    public HeroCard createHeroCard() {
        HeroCardDao heroCardDao = new HeroCardDao();
        HeroCard heroCard = new HeroCard();
        heroCardDao.add(heroCard);
        return heroCard;
    }
}
