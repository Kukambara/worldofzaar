package com.worldofzaar.service;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.11.13
 * Time: 3:05
 * To change this template use File | Settings | File Templates.
 */
public class CardService {
    public void deleteCard(Integer cardId) {
        SupportCardService supportCardService = new SupportCardService();
        WarriorCardService warriorCardService = new WarriorCardService();
        EngCardTextService engCardTextService = new EngCardTextService();
        RuCardTextService ruCardTextService = new RuCardTextService();

        engCardTextService.deleteTextByCardId(cardId);
        ruCardTextService.deleteTextByCardId(cardId);

        supportCardService.deleteCard(cardId);
        warriorCardService.deleteCard(cardId);
    }

}
