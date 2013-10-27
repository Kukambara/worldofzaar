package com.worldofzaar.service;

import com.worldofzaar.adapter.DeckCardAdapter;
import com.worldofzaar.dao.DeckCardDao;
import com.worldofzaar.entity.DeckCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */
public class DeckCardService {

    public List<DeckCardAdapter> getDeckCardsById(Integer deckId){
        DeckCardDao deckCardsDao = new DeckCardDao();

        List<DeckCard> deckCards =  deckCardsDao.getDeckCardsById(deckId);
        List<DeckCardAdapter> deckCardAdapter = new ArrayList<DeckCardAdapter>();

        for (DeckCard tmp : deckCards) {
            deckCardAdapter.add(new DeckCardAdapter(tmp));
        }
        return deckCardAdapter;
    }
}
