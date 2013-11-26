package com.worldofzaar.service;

import com.worldofzaar.dao.EngPropertyTextDao;
import com.worldofzaar.entity.Card;
import com.worldofzaar.entity.EngPropertyText;
import com.worldofzaar.entity.SupportCard;
import com.worldofzaar.entity.WarriorCard;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class EngPropertyTextService {
    public void addText(WarriorCard card, String engProperty) {
        addText((Card) card, engProperty);
    }

    public void addText(SupportCard card, String engProperty) {
        addText((Card) card, engProperty);
    }

    public void addText(Card card, String engProperty) {
        EngPropertyText engPropertyText = new EngPropertyText();
        engPropertyText.setPropertyInfo(engProperty);
        if (card instanceof SupportCard)
            engPropertyText.setSupportCard((SupportCard) card);
        if (card instanceof WarriorCard)
            engPropertyText.setWarriorCard((WarriorCard) card);
        EngPropertyTextDao engPropertyTextDao = new EngPropertyTextDao();
        engPropertyTextDao.add(engPropertyText);
    }

    public void editText(WarriorCard card, String engProperty) {
        editText((Card) card, engProperty);
    }

    public void editText(SupportCard card, String engProperty) {
        editText((Card) card, engProperty);
    }

    private void editText(Card card, String engProperty) {
        EngPropertyTextDao engPropertyTextDao = new EngPropertyTextDao();
        EngPropertyText engPropertyText = engPropertyTextDao.getTextByCardId(card.getCardId());
        if (engPropertyText == null) {
            addText(card, engProperty);
            return;
        }
        engPropertyText.setPropertyInfo(engProperty);
        if (card instanceof SupportCard)
            engPropertyText.setSupportCard((SupportCard) card);
        if (card instanceof WarriorCard)
            engPropertyText.setWarriorCard((WarriorCard) card);
        engPropertyTextDao.update(engPropertyText);
    }

    public void deleteTextByCardId(Integer cardId) {
        EngPropertyTextDao engPropertyTextDao = new EngPropertyTextDao();
        engPropertyTextDao.removeTextByCardId(cardId);
    }

    public EngPropertyText getText(Integer cardId) {
        EngPropertyTextDao engPropertyTextDao = new EngPropertyTextDao();
        return engPropertyTextDao.getTextByCardId(cardId);
    }

}
