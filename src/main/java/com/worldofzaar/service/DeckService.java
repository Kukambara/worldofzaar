package com.worldofzaar.service;

import com.worldofzaar.dao.DeckDao;
import com.worldofzaar.entity.Deck;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class DeckService {

    public List<Deck> getUserDecksById(Integer userId){
        DeckDao deckDao = new DeckDao();
         return deckDao.getUserDecksById(userId);
    }
}
