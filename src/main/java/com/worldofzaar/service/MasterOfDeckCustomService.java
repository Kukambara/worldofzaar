package com.worldofzaar.service;

import com.worldofzaar.adapter.MasterOfDeckAdapter;
import com.worldofzaar.adapter.MasterOfDeckConstructor;
import com.worldofzaar.adapter.SupportCardAdapter;
import com.worldofzaar.adapter.WarriorCardAdapter;
import com.worldofzaar.entity.MasterOfDeck;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 27.10.13
 * Time: 12:17
 * To change this template use File | Settings | File Templates.
 */
public class MasterOfDeckCustomService {

    public List<MasterOfDeckAdapter> getCustomMasterOfDeck(String lang){

        WarriorCardService warriorCardService = new WarriorCardService();
        SupportCardService supportCardService = new SupportCardService();
        MasterOfDeckService masterOfDeckService = new MasterOfDeckService();

        List<SupportCardAdapter> supportCardAdapters = supportCardService.getCompositeSupportCards(lang);
        List<WarriorCardAdapter> warriorCardAdapters = warriorCardService.getCompositeWarriorsCards(lang);
        List<MasterOfDeck> masterOfDeck = masterOfDeckService.getList();


        MasterOfDeckConstructor masterOfDeckConstructor = new MasterOfDeckConstructor(warriorCardAdapters,supportCardAdapters,masterOfDeck);

        return masterOfDeckConstructor.getResultMasterOfDeckAdapter();
    }
}
