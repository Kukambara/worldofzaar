package com.worldofzaar.service;

import com.worldofzaar.dao.GameDao;
import com.worldofzaar.entity.*;
import com.worldofzaar.util.UserInformation;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */
public class GameService {
    public boolean isGameCreated(HttpServletRequest request) {
        UserInformation userInformation = new UserInformation(request);
        GameDao gameDao = new GameDao();
        return gameDao.isMyGameReady(userInformation);
    }

    private Hero createHero(HashMap<Integer, CertainTable> tableMap, int index, int randomUser) {
        boolean isActive = false;
        if (randomUser == index)
            isActive = true;
        HeroService heroService = new HeroService();
        return heroService.createHero(tableMap.get(index).getUser(), isActive);
    }

    public void createNewGame(List<CertainTable> tables) {
        if (tables.size() == 0)
            return;
        Game game = new Game();
        game.setBank(tables.get(0).getTableCost());
        game.setPrivate(false);
        game.setPhase(Phase.active);

        Random random = new Random();
        //Get random active user.
        int randomUser = random.nextInt((tables.size() - 0) + 1) + 0;
        boolean isActive = false;
        HashMap<Integer, CertainTable> tableMap = getHashMapOfTables(tables);
        //Set heroes to the game.
        int index = 0;
        if (!isOutOfBounds(tables, index)) {
            game.setFirstHero(createHero(tableMap, index, randomUser));
        }
        index++;
        if (!isOutOfBounds(tables, index)) {
            game.setSecondHero(createHero(tableMap, index, randomUser));
        }
        index++;
        if (!isOutOfBounds(tables, index)) {
            game.setThirdHero(createHero(tableMap, index, randomUser));
        }
        index++;
        if (!isOutOfBounds(tables, index)) {
            game.setFirstHero(createHero(tableMap, index, randomUser));
        }
        game.setChat(getChat());
        game.setLog(getLog());


    }

    private HashMap<Integer, CertainTable> getHashMapOfTables(List<CertainTable> tables) {
        HashMap<Integer, CertainTable> tableMap = new HashMap<Integer, CertainTable>();
        for (CertainTable ct : tables) {
            tableMap.put(ct.getSeatPosition(), ct);
        }
        return tableMap;
    }

    //Chech if index is out of bounds list.
    private boolean isOutOfBounds(List l, int position) {
        if (l.size() >= position || position < 0)
            return true;
        return false;
    }

    public Chat getChat() {
        ChatService chatService = new ChatService();
        return chatService.createChat();
    }

    public Log getLog() {
        LogService logService = new LogService();
        return logService.createLog();


    }

}
