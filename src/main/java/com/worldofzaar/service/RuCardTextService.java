package com.worldofzaar.service;

import com.worldofzaar.dao.RuCardTextDao;
import com.worldofzaar.entity.Card;
import com.worldofzaar.entity.RuCardText;
import com.worldofzaar.entity.SupportCard;
import com.worldofzaar.entity.WarriorCard;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:35
 * To change this template use File | Settings | File Templates.
 */
public class RuCardTextService {
    public void addText(WarriorCard card, String ruName, String ruSlogan) {
        addText((Card) card, ruName, ruSlogan);
    }

    public void addText(SupportCard card, String ruName, String ruSlogan) {
        addText((Card) card, ruName, ruSlogan);
    }

    private void addText(Card card, String engName, String engSlogan) {
        RuCardText ruCardText = new RuCardText();
        ruCardText.setCardName(engName);
        ruCardText.setCardSlogan(engSlogan);
        if (card instanceof SupportCard)
            ruCardText.setSupportCard((SupportCard) card);
        if (card instanceof WarriorCard)
            ruCardText.setWarriorCard((WarriorCard) card);
        RuCardTextDao ruCardTextDao = new RuCardTextDao();
        ruCardTextDao.add(ruCardText);
    }

    public void editText(WarriorCard card, String ruName, String ruSlogan) {
        editText((Card) card, ruName, ruSlogan);
    }

    public void editText(SupportCard card, String ruName, String ruSlogan) {
        editText((Card) card, ruName, ruSlogan);
    }

    private void editText(Card card, String ruName, String ruSlogan) {
        RuCardTextDao ruCardTextDao = new RuCardTextDao();
        RuCardText ruCardText = ruCardTextDao.getTextByCardId(card.getCardId());
        ruCardText.setCardName(ruName);
        ruCardText.setCardSlogan(ruSlogan);
        if (card instanceof SupportCard)
            ruCardText.setSupportCard((SupportCard) card);
        if (card instanceof WarriorCard)
            ruCardText.setWarriorCard((WarriorCard) card);
        ruCardTextDao.update(ruCardText);
    }

    public void deleteTextByCardId(Integer cardId) {
        RuCardTextDao ruCardTextDao = new RuCardTextDao();
        ruCardTextDao.removeTextByCardId(cardId);
    }

    public RuCardText getText(Integer cardId) {
        RuCardTextDao ruCardTextDao = new RuCardTextDao();
        return ruCardTextDao.getTextByCardId(cardId);
    }
}
