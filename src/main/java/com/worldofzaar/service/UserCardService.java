package com.worldofzaar.service;

import com.worldofzaar.adapter.UserCardAdapter;
import com.worldofzaar.dao.UserCardDao;
import com.worldofzaar.entity.MasterOfDeck;
import com.worldofzaar.entity.User;
import com.worldofzaar.entity.UserCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.10.13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class UserCardService {

    public List<UserCardAdapter> getAllUserCardsById(Integer userId){
        UserCardDao userCardDao = new UserCardDao();

        List<Object[]> userCards = userCardDao.gelAllUserCardsById(userId);
        List<UserCardAdapter> userCardAdapter = new ArrayList<UserCardAdapter>();

        for (Object[] tmp : userCards) {
            userCardAdapter.add(new UserCardAdapter(tmp));
        }
        return userCardAdapter;
    }

    public void buyCard(User user,Integer masterOfDeckId,Boolean isGold){
        Integer val = isGold? 1 : 0;

        UserCardDao userCardDao = new UserCardDao();
        MasterOfDeckService masterOfDeckService = new MasterOfDeckService();
        MasterOfDeck masterOfDeck = masterOfDeckService.getMasterOfDeck(masterOfDeckId);
        GameProfileService gameProfileService = new GameProfileService();
        switch(val){
            case 1:
                if(user.getGameProfile().getDonateMoney()>=masterOfDeck.getDonatePrice()){
                    UserCard userCard = new UserCard();
                    userCard.setUser(user);
                    if(masterOfDeck.getWarriorCard() != null){
                       userCard.setWarriorCard(masterOfDeck.getWarriorCard());
                    } else{
                       userCard.setSupportCard(masterOfDeck.getSupportCard());
                    }
                    userCardDao.add(userCard);
                    user.getGameProfile().setDonateMoney(user.getGameProfile().getDonateMoney()-masterOfDeck.getDonatePrice());
                    gameProfileService.updateGameProfile(user.getGameProfile());
                }
                break;
            case 0:
                if(user.getGameProfile().getMoney()>=masterOfDeck.getPrice()){
                    UserCard userCard = new UserCard();
                    userCard.setUser(user);
                    if(masterOfDeck.getWarriorCard() != null){
                        userCard.setWarriorCard(masterOfDeck.getWarriorCard());
                    } else{
                        userCard.setSupportCard(masterOfDeck.getSupportCard());
                    }
                    userCardDao.add(userCard);
                    user.getGameProfile().setMoney(user.getGameProfile().getMoney() - masterOfDeck.getPrice());
                    gameProfileService.updateGameProfile(user.getGameProfile());
                }
                break;
        }
    }

    public void sellCard(User user,Integer userCardId){
        MasterOfDeckService masterOfDeckService = new MasterOfDeckService();
        UserCardDao userCardDao = new UserCardDao();
        GameProfileService gameProfileService = new GameProfileService();

        UserCard userCard = userCardDao.find(userCardId);
        MasterOfDeck masterOfDeck;

        if(userCard.getWarriorCard() !=null){
            masterOfDeck = masterOfDeckService.getMasterOfDeckByCardId(userCard.getWarriorCard().getCardId());
        }else{
            masterOfDeck = masterOfDeckService.getMasterOfDeckByCardId(userCard.getSupportCard().getCardId());
        }
        user.getGameProfile().setMoney(user.getGameProfile().getMoney()+(int)(masterOfDeck.getPrice()*0.5));

        userCardDao.remove(userCard);
        gameProfileService.updateGameProfile(user.getGameProfile());
    }
}
