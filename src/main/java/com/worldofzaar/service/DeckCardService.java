package com.worldofzaar.service;

import com.worldofzaar.dao.DeckCardDao;
import com.worldofzaar.entity.DeckCard;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */
public class DeckCardService {

    public List<DeckCard> getDeckCardsById(Integer deckId){
        DeckCardDao deckCards = new DeckCardDao();
        return deckCards.getDeckCardsById(deckId);
    }
}
