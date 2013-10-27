package com.worldofzaar.adapter;

import com.worldofzaar.entity.MasterOfDeck;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public class AbstractMasterOfDeckAdapter {

    HashMap<Integer, MasterOfDeckAdapter> masterOfDeckAdapter;


    public AbstractMasterOfDeckAdapter(List<Object[]> inputWarriorCards, List<Object[]> inputSupportCards, List<MasterOfDeck> inputMasterOfDeck) {

        setMasterOfDeckAdapter(inputMasterOfDeck);
    }

    public void setMasterOfDeckAdapter(List<MasterOfDeck> inputMasterOfDeck){
         for(MasterOfDeck tmp : inputMasterOfDeck){

         }
    }

    public void SetAllWarriorCard(List<Object[]> inputWarriorCards) {

        for(Object[] tmp:inputWarriorCards){
            //warriorSet[(Integer)tmp[0]] = new WarriorCardAdapter(tmp);
        }
    }

    public void SetAllSupportCard( List<Object[]> inputSupportCards) {

    }

}
