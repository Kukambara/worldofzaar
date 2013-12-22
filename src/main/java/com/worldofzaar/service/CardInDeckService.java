package com.worldofzaar.service;

import com.worldofzaar.adapter.DeckCardAdapter;
import com.worldofzaar.dao.CardInDeckDao;
import com.worldofzaar.entity.CardInDeck;
import com.worldofzaar.entity.SupportCard;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.UserInformation;

import java.util.ArrayList;
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

    public void removeCard(Integer cardsInDeck){
        CardInDeckDao cardsInDeckDao = new CardInDeckDao();
        CardInDeck toDelete = cardsInDeckDao.find(cardsInDeck);
        cardsInDeckDao.remove(toDelete);
    }

    public void addCard(Integer deckId, Integer userCardId){
        CardInDeckDao cardInDeckDao = new CardInDeckDao();

        DeckService deckService = new DeckService();
        CardInDeck cardInDeck = new CardInDeck();
        UserCardService userCardService = new UserCardService();

        cardInDeck.setDeck(deckService.getDeck(deckId));
        cardInDeck.setCard(userCardService.fingUserCardById(userCardId));

        cardInDeckDao.add(cardInDeck);
    }

    public List<DeckCardAdapter> getCardsByDeckId(Integer deckId){
        CardInDeckDao cardInDeckDao = new CardInDeckDao();

        List<CardInDeck> cardInDecks =  cardInDeckDao.getDeckCardsById(deckId);
        List<DeckCardAdapter> deckCardAdapter = new ArrayList<DeckCardAdapter>();

        for (CardInDeck tmp : cardInDecks) {
            deckCardAdapter.add(new DeckCardAdapter(tmp));
        }
        return deckCardAdapter;
    }
}
