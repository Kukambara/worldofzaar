package com.worldofzaar.service;

import com.worldofzaar.dao.MasterOfDeckDao;
import com.worldofzaar.entity.MasterOfDeck;
import com.worldofzaar.entity.SupportCard;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.wrapper.MasterOfDeckWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class MasterOfDeckService {

    public List<MasterOfDeck> getList() {
        MasterOfDeckDao masterOfDeckDao = new MasterOfDeckDao();
        return masterOfDeckDao.list();
    }

    public MasterOfDeck getMasterOfDeck(Integer masterOfDeckId) {
        MasterOfDeckDao masterOfDeckDao = new MasterOfDeckDao();
        return masterOfDeckDao.find(masterOfDeckId);
    }

    public MasterOfDeck getMasterOfDeckByCardId(Integer cardId) {
        MasterOfDeckDao masterOfDeckDao = new MasterOfDeckDao();
        return masterOfDeckDao.findByCardId(cardId);
    }

    public void createWarrMasterOfDeck(Integer cardId, Integer cardLevel, Integer price, Integer donatePrice) {

        //Don't repeat Master of card for the same card.
        if (getMasterOfDeckByCardId(cardId) != null)
            return;

        WarriorCardService warriorCardService = new WarriorCardService();
        WarriorCard warriorCard = warriorCardService.getCard(cardId);
        MasterOfDeck masterOfDeck = new MasterOfDeck();
        masterOfDeck.setWarriorCard(warriorCard);
        masterOfDeck.setCardLevel(cardLevel);
        masterOfDeck.setPrice(price);
        masterOfDeck.setDonatePrice(donatePrice);
        MasterOfDeckDao masterOfDeckDao = new MasterOfDeckDao();
        masterOfDeckDao.add(masterOfDeck);
    }

    public void createSuppMasterOfDeck(Integer cardId, Integer cardLevel, Integer price, Integer donatePrice) {

        //Don't repeat Master of card for the same card.
        if (getMasterOfDeckByCardId(cardId) != null)
            return;

        SupportCardService supportCardService = new SupportCardService();
        SupportCard supportCard = supportCardService.getCard(cardId);
        MasterOfDeck masterOfDeck = new MasterOfDeck();
        masterOfDeck.setSupportCard(supportCard);
        masterOfDeck.setCardLevel(cardLevel);
        masterOfDeck.setPrice(price);
        masterOfDeck.setDonatePrice(donatePrice);
        MasterOfDeckDao masterOfDeckDao = new MasterOfDeckDao();
        masterOfDeckDao.add(masterOfDeck);
    }

    public void editMasterOfDeck(Integer masterofDeckId, Integer cardId, Integer cardLevel, Integer price, Integer donatePrice) {
        MasterOfDeckDao masterOfDeckDao = new MasterOfDeckDao();
        MasterOfDeck masterOfDeck = masterOfDeckDao.find(masterofDeckId);

        SupportCardService supportCardService = new SupportCardService();
        WarriorCardService warriorCardService = new WarriorCardService();
        SupportCard supportCard = supportCardService.getCard(cardId);
        WarriorCard warriorCard = null;
        if (supportCard == null)
            warriorCard = warriorCardService.getCard(cardId);

        masterOfDeck.setWarriorCard(warriorCard);
        masterOfDeck.setSupportCard(supportCard);
        masterOfDeck.setCardLevel(cardLevel);
        masterOfDeck.setPrice(price);
        masterOfDeck.setDonatePrice(donatePrice);

        masterOfDeckDao.update(masterOfDeck);
    }

    public void deleteMasterOfDeck(Integer masterofDeckId) {
        MasterOfDeckDao masterOfDeckDao = new MasterOfDeckDao();
        masterOfDeckDao.remove(masterofDeckId);
    }

    public List<MasterOfDeckWrapper> getMasterWrapperList() {
        MasterOfDeckDao masterOfDeckDao = new MasterOfDeckDao();
        List<Object[]> list = masterOfDeckDao.listForWrapper();
        List<MasterOfDeckWrapper> wrapperList = new ArrayList<MasterOfDeckWrapper>();
        for (Object[] o : list) {
            MasterOfDeckWrapper wrapperMasterOfDeck = new MasterOfDeckWrapper(o);
            wrapperList.add(wrapperMasterOfDeck);
        }
        return wrapperList;
    }
}
