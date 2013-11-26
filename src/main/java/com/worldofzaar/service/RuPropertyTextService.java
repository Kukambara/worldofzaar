package com.worldofzaar.service;

import com.worldofzaar.dao.RuPropertyTextDao;
import com.worldofzaar.entity.Card;
import com.worldofzaar.entity.RuPropertyText;
import com.worldofzaar.entity.SupportCard;
import com.worldofzaar.entity.WarriorCard;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public class RuPropertyTextService {
    public void addText(WarriorCard card, String ruProperty) {
        addText((Card) card, ruProperty);
    }

    public void addText(SupportCard card, String ruProperty) {
        addText((Card) card, ruProperty);
    }

    public void addText(Card card, String ruProperty) {
        RuPropertyText ruPropertyText = new RuPropertyText();
        ruPropertyText.setPropertyInfo(ruProperty);
        if (card instanceof SupportCard)
            ruPropertyText.setSupportCard((SupportCard) card);
        if (card instanceof WarriorCard)
            ruPropertyText.setWarriorCard((WarriorCard) card);
        RuPropertyTextDao ruPropertyTextDao = new RuPropertyTextDao();
        ruPropertyTextDao.add(ruPropertyText);
    }

    public void editText(WarriorCard card, String engProperty) {
        editText((Card) card, engProperty);
    }

    public void editText(SupportCard card, String engProperty) {
        editText((Card) card, engProperty);
    }

    private void editText(Card card, String ruProperty) {
        RuPropertyTextDao ruPropertyTextDao = new RuPropertyTextDao();
        RuPropertyText ruPropertyText = ruPropertyTextDao.getTextByCardId(card.getCardId());
        if (ruPropertyText == null) {
            addText(card, ruProperty);
            return;
        }
        ruPropertyText.setPropertyInfo(ruProperty);
        if (card instanceof SupportCard)
            ruPropertyText.setSupportCard((SupportCard) card);
        if (card instanceof WarriorCard)
            ruPropertyText.setWarriorCard((WarriorCard) card);
        ruPropertyTextDao.update(ruPropertyText);
    }

    public void deleteTextByCardId(Integer cardId) {
        RuPropertyTextDao ruPropertyTextDao = new RuPropertyTextDao();
        ruPropertyTextDao.removeTextByCardId(cardId);
    }

    public RuPropertyText getText(Integer cardId) {
        RuPropertyTextDao ruPropertyTextDao = new RuPropertyTextDao();
        return ruPropertyTextDao.getTextByCardId(cardId);
    }


}
