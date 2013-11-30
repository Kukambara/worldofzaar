package com.worldofzaar.adapter;

import com.worldofzaar.entity.RaceText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.11.13
 * Time: 12:41
 * To change this template use File | Settings | File Templates.
 */
public class RaceConstructor {
    public RaceConstructor(){

    }

    public List<RaceAdapter> createRaceAdapterList(List<RaceText> inputRaces){
        List<RaceAdapter> raceAdapters = new ArrayList<RaceAdapter>();
        for (RaceText tmp : inputRaces) {
            raceAdapters.add(new RaceAdapter(tmp));
        }
        return raceAdapters;
    }

}
