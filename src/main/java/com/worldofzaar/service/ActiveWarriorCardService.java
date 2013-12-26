package com.worldofzaar.service;

import com.worldofzaar.dao.ActiveWarriorCardDao;
import com.worldofzaar.entity.ActiveWarriorCard;
import com.worldofzaar.entity.WarriorCard;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:43
 * To change this template use File | Settings | File Templates.
 */
public class ActiveWarriorCardService {
    public ActiveWarriorCard create(Integer warriorCardId) {
        ActiveWarriorCardDao activeWarriorCardDao = new ActiveWarriorCardDao();
        WarriorCardService warriorCardService = new WarriorCardService();
        WarriorCard warriorCard = warriorCardService.getCard(warriorCardId);
        if (warriorCard == null)
            return null;
        ActiveWarriorCard activeWarriorCard = new ActiveWarriorCard();
        activeWarriorCard.setCurrentArmor(warriorCard.getCardArmor());
        activeWarriorCard.setCurrentHealth(warriorCard.getCardHealth());
        activeWarriorCard.setCurrentAttack(warriorCard.getCardDamage());
        activeWarriorCard.setWarriorCardId(warriorCard.getCardId());
        activeWarriorCard.setStepCount(1);
        activeWarriorCardDao.add(activeWarriorCard);

        return activeWarriorCard;
    }

    public void updateCard(ActiveWarriorCard card) {
        ActiveWarriorCardDao activeWarriorCardDao = new ActiveWarriorCardDao();
        activeWarriorCardDao.update(card);
    }

}
