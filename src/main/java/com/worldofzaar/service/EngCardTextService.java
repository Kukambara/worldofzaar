package com.worldofzaar.service;

import com.worldofzaar.dao.EngCardTextDao;
import com.worldofzaar.entity.Card;
import com.worldofzaar.entity.EngCardText;
import com.worldofzaar.entity.SupportCard;
import com.worldofzaar.entity.WarriorCard;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class EngCardTextService {
    public void addText(WarriorCard card, String engName, String engSlogan) {
        addText((Card)card, engName, engSlogan);
    }

    public void addText(SupportCard card, String engName, String engSlogan) {
        addText((Card)card, engName, engSlogan);
    }

    private void addText(Card card, String engName, String engSlogan) {
        EngCardText engCardText = new EngCardText();
        engCardText.setCardName(engName);
        engCardText.setCardSlogan(engSlogan);
        if (card instanceof SupportCard)
            engCardText.setSupportCard((SupportCard) card);
        if (card instanceof WarriorCard)
            engCardText.setWarriorCard((WarriorCard) card);
        EngCardTextDao engCardTextDao = new EngCardTextDao();
        engCardTextDao.add(engCardText);
    }
}
