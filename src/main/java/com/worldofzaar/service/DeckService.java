package com.worldofzaar.service;

import com.worldofzaar.adapter.DeckAdapter;
import com.worldofzaar.dao.DeckDao;
import com.worldofzaar.entity.Deck;
import com.worldofzaar.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class DeckService {

    public List<DeckAdapter> getUserDecksById(Integer userId) {
        DeckDao deckDao = new DeckDao();
        List<Deck> decks = deckDao.getUserDecksById(userId);
        List<DeckAdapter> deckAdapters = new ArrayList<DeckAdapter>();
        for (Deck tmp : decks) {
            deckAdapters.add(new DeckAdapter(tmp));
        }
        return deckAdapters;
    }

    public void createDeck(String deckName, User user) {
        createDeck(deckName, user, false);
    }

    public void createDeck(String deckName, User user, Boolean active) {
        DeckDao deckDao = new DeckDao();
        Deck deck = new Deck();
        deck.setDeckName(deckName);
        deck.setActive(Boolean.FALSE);
        deck.setUser(user);
        deck.setActive(active);
        deckDao.add(deck);
    }

    public Deck getDeck(Integer deckId) {
        DeckDao deckDao = new DeckDao();
        return deckDao.find(deckId);
    }


}
