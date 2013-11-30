package com.worldofzaar.service;

import com.worldofzaar.entity.CertainTable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */
public class GameService {
    public void createNewGame(List<CertainTable> tables) {
        HeroService heroService = new HeroService();
        heroService.createHero(null);


    }

}
