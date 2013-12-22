package com.worldofzaar.service;

import com.worldofzaar.dao.UserDao;
import com.worldofzaar.entity.GameProfile;
import com.worldofzaar.entity.User;
import com.worldofzaar.util.WOZConsts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class UserService {

    public User getUserGameProfileById(Integer userId) {
        UserDao gameProfileDao = new UserDao();
        return gameProfileDao.getUserGameProfilesById(userId);
    }

    public User getUser(Integer userId) {
        UserDao userDao = new UserDao();
        return userDao.find(userId);
    }

    public Boolean checkUserNickname(String nickname){
        UserDao userDao = new UserDao();
        return userDao.checkUserNickname(nickname);
    }

    public void createUser(String userName, Integer blazonId, Integer racePictureId, boolean isMale,
                           HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = new UserDao();
        GameProfileService gameProfileService = new GameProfileService();
        GameProfile gameProfile = gameProfileService.addGameProfile(blazonId, racePictureId, isMale);

        //Stop creation User if gameProfile didn't create.
        if (gameProfile == null)
            return;
        User user = new User();
        user.setGameProfile(gameProfile);
        user.setUserName(userName);
        userDao.add(user);

        DeckService deckService = new DeckService();
        //Create deck with name New deck,  user = user, and active = true
        deckService.createDeck("New deck", user, true);

        WebUserService webUserService = new WebUserService();
        webUserService.setUser((Integer) request.getSession().getAttribute(WOZConsts.WEBUSER_ID), user);

        AuthorizationService authorizationService = new AuthorizationService();
        authorizationService.loginByCookies(request, response);
    }

    public void updateUser(User user){
        UserDao userDao = new UserDao();
        userDao.update(user);
    }
}
