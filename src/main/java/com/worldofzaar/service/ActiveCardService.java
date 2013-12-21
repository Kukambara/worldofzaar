package com.worldofzaar.service;

import com.worldofzaar.dao.ActiveCardDao;
import com.worldofzaar.entity.ActiveCard;
import com.worldofzaar.entity.CardInDeck;
import com.worldofzaar.entity.Hero;
import com.worldofzaar.entity.enums.Location;
import com.worldofzaar.util.WOZConsts;

import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:43
 * To change this template use File | Settings | File Templates.
 */
public class ActiveCardService {
    private final int cardsInHand = 5;

    public void createActiveCards(Hero hero) {
        ActiveCardDao activeCardDao = new ActiveCardDao();
        CardInDeckService cardInDeckService = new CardInDeckService();
        List<CardInDeck> cards = cardInDeckService.getCardsFromActiveDeck(hero.getUserId());

        //If deck doesn't full.
        if (cards.size() < WOZConsts.MINIMUM_CARDS_COUNT) {
            return;
        }

        Random random = new Random();

        int randomCard = 0;
        for (int i = 0; i < cards.size(); i++) {
            //Get random active card.
            randomCard = random.nextInt(cards.size());
            CardInDeck cardInDeck = cards.get(randomCard);
            //Create active card.
            ActiveCard activeCard = new ActiveCard();
            activeCard.setHero(hero);

            if (cardInDeck.getCard().getSupportCard() != null)
                activeCard.setSupportCardId(cardInDeck.getCard().getSupportCard().getCardId());
            if (cardInDeck.getCard().getWarriorCard() != null) {
                ActiveWarriorCardService activeWarriorCardService = new ActiveWarriorCardService();
                activeCard.setActiveWarriorCard(activeWarriorCardService
                        .create(cardInDeck.getCard().getWarriorCard().getCardId()));

            }


            activeCard.setPosition(i);

            //Set first 5 cards - hand = true, first 5 cards already in user's hands.
            if (i < cardsInHand) {
                activeCard.setLocation(Location.hand);
            } else {
                activeCard.setLocation(Location.deck);
            }
            activeCardDao.add(activeCard);
            cards.remove(randomCard);
        }
    }

    public List<ActiveCard> getUserActiveCardsInHand(Integer userId) {
        ActiveCardDao activeCardDao = new ActiveCardDao();
        return activeCardDao.getUserActiveCardsInHand(userId);
    }

    public ActiveCard getCard(Integer cardId) {
        ActiveCardDao activeCardDao = new ActiveCardDao();
        return activeCardDao.find(cardId);
    }

    public void updateCard(ActiveCard card) {
        ActiveCardDao activeCardDao = new ActiveCardDao();
        activeCardDao.update(card);
    }
}
