package com.worldofzaar.service;

import com.worldofzaar.dao.HeroCardDao;
import com.worldofzaar.entity.ActiveCard;
import com.worldofzaar.entity.Hero;
import com.worldofzaar.entity.HeroCard;
import com.worldofzaar.entity.enums.Activity;
import com.worldofzaar.entity.enums.Location;
import com.worldofzaar.modelAttribute.CardPosition;
import com.worldofzaar.util.UserInformation;

import javax.servlet.http.HttpServletRequest;

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

    public void putCard(CardPosition cardPosition, HttpServletRequest request) {
        UserInformation userInformation = new UserInformation(request);
        HeroCardDao heroCardDao = new HeroCardDao();
        HeroService heroService = new HeroService();
        HeroCard heroCard = heroCardDao.getHeroCards(userInformation);
        ActiveCardService activeCardService = new ActiveCardService();
        ActiveCard activeCard = activeCardService.getCard(cardPosition.getCardId());

        if (activeCard == null || activeCard.getHero().getUserId().equals(userInformation.getUserId()))
            return;
        Hero hero = activeCard.getHero();
        if (hero.getActivity() != Activity.ACTIVE || activeCard.getLocation() != Location.HAND)
            return;
        if (hero.getEnergy() < activeCard.getEnergy())
            return;
        switch (cardPosition.getPosition()) {
            case 0:
                if (heroCard.getSupportCard() == null && activeCard.isSupport())
                    heroCard.setSupportCard(activeCard);
                break;
            case 1:
                if (heroCard.getFirstActiveWarriorCard() == null && activeCard.isWarrior())
                    heroCard.setFirstActiveWarriorCard(activeCard);
                break;
            case 2:
                if (heroCard.getSecondActiveWarriorCard() == null && activeCard.isWarrior())
                    heroCard.setSecondActiveWarriorCard(activeCard);
                break;
            case 3:
                if (heroCard.getThirdActiveWarriorCard() == null && activeCard.isWarrior())
                    heroCard.setThirdActiveWarriorCard(activeCard);
                break;
        }
        heroCardDao.update(heroCard);
        hero.setEnergy(hero.getEnergy() - activeCard.getEnergy());
        heroService.updateHero(hero);
    }

}
