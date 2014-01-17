package com.worldofzaar.service;

import com.worldofzaar.dao.GameDao;
import com.worldofzaar.entity.*;
import com.worldofzaar.entity.enums.Activity;
import com.worldofzaar.entity.enums.Phase;
import com.worldofzaar.entity.enums.State;
import com.worldofzaar.modelAttribute.CardPosition;
import com.worldofzaar.modelAttribute.Move;
import com.worldofzaar.util.UserInformation;
import com.worldofzaar.wrapper.GameWrapper;

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
        return gameDao.isGameCreated(userInformation);
    }

    public boolean isGameReady(HttpServletRequest request) {
        UserInformation userInformation = new UserInformation(request);
        GameDao gameDao = new GameDao();
        return gameDao.isGameReady(userInformation);
    }

    private Hero createHero(HashMap<Integer, CertainTable> tableMap, int index, int randomUser) {
        Activity activity = Activity.UNACTIVE;
        if (randomUser == index)
            activity = Activity.ACTIVE;
        HeroService heroService = new HeroService();
        if (tableMap.size() < index)
            return null;
        return heroService.createHero(tableMap.get(index).getUser(), activity);

    }

    public void createNewGame(List<CertainTable> tables) {
        if (tables.size() == 0)
            return;
        Game game = new Game();
        game.setBank(tables.get(0).getTableCost());
        game.setState(State.LOADING);
        game.setPhase(Phase.ACTIVE);
        game.setBank(tables.get(0).getTableCost() * tables.size());

        Random random = new Random();
        //Get random active user.
        int randomUser = random.nextInt(tables.size());
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
            game.setFourthHero(createHero(tableMap, index, randomUser));
        }
        game.setChat(getChat());
        game.setLog(getLog());
        game.setReady(false);
        game.setFirstCircleHeroIndex(randomUser);
        game.setIndexOfActiveHero(randomUser);
        GameDao gameDao = new GameDao();
        gameDao.add(game);
    }

    private HashMap<Integer, CertainTable> getHashMapOfTables(List<CertainTable> tables) {
        HashMap<Integer, CertainTable> tableMap = new HashMap<Integer, CertainTable>();
        for (CertainTable ct : tables) {
            tableMap.put(ct.getSeatPosition(), ct);
        }
        return tableMap;
    }

    //Check if index is out of bounds list.
    private boolean isOutOfBounds(List l, int position) {
        if (position >= l.size() || position < 0)
            return true;
        return false;
    }

    private Chat getChat() {
        ChatService chatService = new ChatService();
        return chatService.createChat();
    }

    private Log getLog() {
        LogService logService = new LogService();
        return logService.createLog();
    }

    public Game getGame(HttpServletRequest request) {
        UserInformation userInformation = new UserInformation(request);
        GameDao gameDao = new GameDao();
        return gameDao.getGame(userInformation.getUserId());
    }

    public void gameIsReady(Game game, HttpServletRequest request) {
        if (game == null)
            return;
        boolean isReady = true;
        if (game.getFirstHero() != null)
            isReady &= (game.getFirstHero().getActivity() == Activity.READY);
        if (game.getSecondHero() != null)
            isReady &= (game.getSecondHero().getActivity() == Activity.READY);
        if (game.getThirdHero() != null)
            isReady &= (game.getThirdHero().getActivity() == Activity.READY);
        if (game.getFourthHero() != null)
            isReady &= (game.getFourthHero().getActivity() == Activity.READY);

        if (isReady) {
            GameDao gameDao = new GameDao();
            UserInformation userInformation = new UserInformation(request);
            gameDao.gameIsReady(userInformation);
        }
    }

    public void endMove(HttpServletRequest request) {
        GameDao gameDao = new GameDao();
        UserInformation userInformation = new UserInformation(request);
        Game game = gameDao.getGame(userInformation.getUserId());
        Hero nextActiveHero = null;
        Hero activeHero = null;
        if (game == null)
            return;

        activeHero = game.getActiveHero();
        nextActiveHero = game.getNextActiveHero();

        activeHero.setActivity(Activity.UNACTIVE);
        nextActiveHero.setActivity(Activity.ACTIVE);


        if (game.getFirstCircleHeroIndex() == game.getPositionByHero(nextActiveHero)) {
            if (game.getPhase() == Phase.END)
                changeCircle(game);
            game.increasePhase();
        }

        HeroService heroService = new HeroService();
        heroService.updateHero(activeHero);
        heroService.updateHero(nextActiveHero);

        game.setStep(game.getStep() + 1);
        gameDao.update(game);
    }

    public void changeCircle(Game game) {
        Hero activeHero = game.getActiveHero();
        Hero nextActiveHero = game.getNextActiveHero();
        activeHero.setActivity(Activity.UNACTIVE);
        nextActiveHero.setActivity(Activity.ACTIVE);
        game.setFirstCircleHeroIndex(game.getPositionByHero(nextActiveHero));
    }

    public void putCard(HttpServletRequest request, CardPosition cardPosition) {
        HeroCardService heroCardService = new HeroCardService();
        heroCardService.putCard(cardPosition, request);
        endMove(request);
    }

    public boolean move(HttpServletRequest request, Move move) {
        //Stop move if move is wrong
        if (!move.valid()) return false;
        Game game = getGame(request);
        UserInformation userInformation = new UserInformation(request);
        GameWrapper gameWrapper = new GameWrapper(game, userInformation);
        return gameWrapper.move(move);
    }

    public boolean throwOff(HttpServletRequest request, com.worldofzaar.modelAttribute.Card card) {
        if (!card.valid()) return false;
        Game game = getGame(request);
        UserInformation userInformation = new UserInformation(request);
        GameWrapper gameWrapper = new GameWrapper(game, userInformation);
        if (gameWrapper.throwOff(card)) {
            endMove(request);
            return true;
        }
        return false;
    }

    public boolean skipMove(HttpServletRequest request) {
        Game game = getGame(request);
        UserInformation userInformation = new UserInformation(request);
        GameWrapper gameWrapper = new GameWrapper(game, userInformation);
        if (gameWrapper.skipMove()) {
            endMove(request);
            return true;
        }
        return false;
    }


}


