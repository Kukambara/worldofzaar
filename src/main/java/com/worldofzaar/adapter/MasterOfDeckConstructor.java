package com.worldofzaar.adapter;

import com.worldofzaar.entity.MasterOfDeck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public class MasterOfDeckConstructor {

    private HashMap<Integer, MasterOfDeckAdapter> masterOfDeckAdapter;


    public MasterOfDeckConstructor(List<WarriorCardAdapter> inputWarriorCards, List<SupportCardAdapter> inputSupportCards, List<MasterOfDeck> inputMasterOfDeck) {
        masterOfDeckAdapter = new HashMap<Integer, MasterOfDeckAdapter>();
        setMasterOfDeckAdapter(inputMasterOfDeck);
        SetAllCards(inputWarriorCards, inputSupportCards);
    }

    public void setMasterOfDeckAdapter(List<MasterOfDeck> inputMasterOfDeck) {
        for (MasterOfDeck tmp : inputMasterOfDeck) {
            Integer getId = -1;
            if (tmp.getWarriorCard() != null) {
                getId = tmp.getWarriorCard().getCardId();
            } else if (tmp.getSupportCard() != null) {
                getId = tmp.getSupportCard().getCardId();
            }

            if (getId != -1) {
                masterOfDeckAdapter.put(getId, new MasterOfDeckAdapter(tmp));
            }
        }

    }

    public void SetAllCards(List<WarriorCardAdapter> inputWarriorCards, List<SupportCardAdapter> inputSupportCards) {

        for (WarriorCardAdapter tmp : inputWarriorCards) {
            if(masterOfDeckAdapter.get(tmp.getCardId()) != null) {
            masterOfDeckAdapter.get(tmp.getCardId()).setWarriorCard(tmp);
            }
        }

        for (SupportCardAdapter tmp : inputSupportCards) {
            if(masterOfDeckAdapter.get(tmp.getCardId()) != null) {
            masterOfDeckAdapter.get(tmp.getCardId()).setSupportCard(tmp);
            }
        }
    }

    public List<MasterOfDeckAdapter> getResultMasterOfDeckAdapter() {
        List<MasterOfDeckAdapter> resultMasterOfDeckAdapters = new ArrayList<MasterOfDeckAdapter>();
        for (Integer key: masterOfDeckAdapter.keySet()) {
            resultMasterOfDeckAdapters.add(masterOfDeckAdapter.get(key));
        }

        return resultMasterOfDeckAdapters;
    }
}
