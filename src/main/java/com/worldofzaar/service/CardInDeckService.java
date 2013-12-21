package com.worldofzaar.service;

import com.worldofzaar.dao.CardInDeckDao;
import com.worldofzaar.entity.CardInDeck;
import com.worldofzaar.util.UserInformation;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 02.12.13
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public class CardInDeckService {
    public int getCountOfActiveDeckCards(UserInformation userInformation) {
        CardInDeckDao cardInDeckDao = new CardInDeckDao();
        return cardInDeckDao.getActiveDeckCardCount(userInformation);
    }

    public List<CardInDeck> getCardsFromActiveDeck(Integer userId) {
        CardInDeckDao cardInDeckDao = new CardInDeckDao();
        return cardInDeckDao.getUserActiveCards(userId);
    }
}
