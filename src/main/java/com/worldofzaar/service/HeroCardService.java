package com.worldofzaar.service;

import com.worldofzaar.dao.HeroCardDao;
import com.worldofzaar.entity.*;
import com.worldofzaar.modelAttribute.CardPosition;
import com.worldofzaar.modelAttribute.Move;
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
        HeroCard heroCard = heroCardDao.getHeroCards(userInformation);
        ActiveCardService activeCardService = new ActiveCardService();
        ActiveCard activeCard = activeCardService.getCard(cardPosition.getCardId());
        if (activeCard == null || activeCard.getHero().getUserId().equals(userInformation.getUserId()))
            return;
        switch (cardPosition.getPosition()) {
            case 0:
                if (heroCard.getSupportCard() == null)
                    heroCard.setSupportCard(activeCard);
                break;
            case 1:
                if (heroCard.getFirstActiveWarriorCard() == null)
                    heroCard.setFirstActiveWarriorCard(activeCard);
                break;
            case 2:
                if (heroCard.getSecondActiveWarriorCard() == null)
                    heroCard.setSecondActiveWarriorCard(activeCard);
                break;
            case 3:
                if (heroCard.getThirdActiveWarriorCard() == null)
                    heroCard.setThirdActiveWarriorCard(activeCard);
                break;
        }
        heroCardDao.update(heroCard);
    }

    public void move(HttpServletRequest request, Move move) {
        if (!move.valid())
            return;

        UserInformation userInformation = new UserInformation(request);
        HeroCardDao heroCardDao = new HeroCardDao();
        HeroCard thisHeroCard = heroCardDao.getHeroCards(userInformation);
        HeroCard enemyHeroCard = heroCardDao.getHeroCards(move.getEnemyId());
        ActiveCard myCard = thisHeroCard.getCard(move.getMyCardId());
        if (myCard == null)
            return;
        ActiveCard enemyCard = enemyHeroCard.getCard(move.getEnemyCardId());
        if (move.isCardAttack())
            attackCard(myCard, enemyCard);
        else
            attackHero(myCard, enemyCard.getHero());

    }

    private void attackCard(ActiveCard myCard, ActiveCard enemyCard) {

    }

    private void attackHero(ActiveCard myCard, Hero enemy) {

    }
}
