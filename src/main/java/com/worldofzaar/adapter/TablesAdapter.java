package com.worldofzaar.adapter;

import com.worldofzaar.entity.CertainTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 01.12.13
 * Time: 16:30
 * To change this template use File | Settings | File Templates.
 */
public class TablesAdapter {
    List<CertainTableAdapter> fourPlayers;
    List<CertainTableAdapter> threePlayers;
    List<CertainTableAdapter> twoPlayers;

    public TablesAdapter(List<CertainTable> tables) {
        fourPlayers = new ArrayList<CertainTableAdapter>();
        threePlayers = new ArrayList<CertainTableAdapter>();
        twoPlayers = new ArrayList<CertainTableAdapter>();

        HashMap<Integer, CertainTable> fourTableMap = new HashMap<Integer, CertainTable>();
        HashMap<Integer, CertainTable> threeTableMap = new HashMap<Integer, CertainTable>();
        HashMap<Integer, CertainTable> twoTableMap = new HashMap<Integer, CertainTable>();

        for (CertainTable table : tables) {
            if (table.getTableSize() == 4)
                fourTableMap.put(table.getSeatPosition(), table);
            else if (table.getTableSize() == 3)
                threeTableMap.put(table.getSeatPosition(), table);
            else if (table.getTableSize() == 2)
                twoTableMap.put(table.getSeatPosition(), table);
        }

        for (int i = 0; i < 4; i++) {
            fourPlayers.add(new CertainTableAdapter(fourTableMap.get(i)));
        }

        for (int i = 0; i < 3; i++) {
            threePlayers.add(new CertainTableAdapter(threeTableMap.get(i)));
        }

        for (int i = 0; i < 2; i++) {
            twoPlayers.add(new CertainTableAdapter(twoTableMap.get(i)));
        }
    }

    public List<CertainTableAdapter> getFourPlayers() {
        return fourPlayers;
    }

    public void CertainTableAdapter(List<CertainTable> fourPlayers) {
        this.fourPlayers = CertainTableAdapter.getTables(fourPlayers);
    }

    public List<CertainTableAdapter> getThreePlayers() {
        return threePlayers;
    }

    public void setThreePlayers(List<CertainTable> threePlayers) {
        this.threePlayers = CertainTableAdapter.getTables(threePlayers);
    }

    public List<CertainTableAdapter> getTwoPlayers() {
        return twoPlayers;
    }

    public void setTwoPlayers(List<CertainTable> twoPlayers) {
        this.twoPlayers = CertainTableAdapter.getTables(twoPlayers);
    }
}
