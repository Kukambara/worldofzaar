package com.worldofzaar.controller;

import com.worldofzaar.adapter.*;
import com.worldofzaar.entity.GameProfile;
import com.worldofzaar.entity.RacePicture;
import com.worldofzaar.service.*;
import com.worldofzaar.util.UserInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 24.10.13
 * Time: 17:13
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/profile")
public class UserController {

    @RequestMapping(value = "/userDecks/", method = RequestMethod.GET)
    public
    @ResponseBody
    List<DeckAdapter> getUserDecks(ModelMap model, HttpServletRequest request) {
        DeckService deckService = new DeckService();
        UserInformation userInformation = new UserInformation(request);
        return deckService.getUserDecksById(userInformation.getUserId());
    }

    @RequestMapping(value = "/userCards/", method = RequestMethod.GET)
    public
    @ResponseBody
    List<UserCardAdapter> getUserCards(ModelMap model, HttpServletRequest request) {
        UserCardService userCardService = new UserCardService();
        UserInformation userInformation = new UserInformation(request);
        return userCardService.getAllUserCardsById(userInformation.getUserId());
    }

    @RequestMapping(value = "/deckCards/{deckId}", method = RequestMethod.POST)
    public
    @ResponseBody
    List<DeckCardAdapter> getDeckCards(ModelMap model, HttpServletRequest request, @PathVariable Integer deckId) {
        DeckCardService deckCardService = new DeckCardService();
        return deckCardService.getDeckCardsById(deckId);
    }

    @RequestMapping(value = "/createUser/{nickname}/{blazonId}/{pictureId}/{isMale}", method = RequestMethod.POST)
    public String createTextUser(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String nickname,
                                 @PathVariable Integer blazonId, @PathVariable Integer pictureId, @PathVariable Integer isMale) {
        UserService userService = new UserService();
        userService.createUser(nickname, blazonId, pictureId, (isMale == 1), request, response);
        return "redirect:/";
    }


    @RequestMapping(value = "/masterOfDeckCards/", method = RequestMethod.GET)
    public
    @ResponseBody
    List<MasterOfDeckAdapter> getAllCards(ModelMap model, HttpServletRequest request) {
        MasterOfDeckCustomService masterOfDeckCustomService = new MasterOfDeckCustomService();

        return masterOfDeckCustomService.getCustomMasterOfDeck("Ru");
    }

    @RequestMapping(value = "/gameProfile/", method = RequestMethod.GET)
    public
    @ResponseBody
    GameProfile getUserProfile(ModelMap model, HttpServletRequest request) {
        UserService userService = new UserService();
        UserInformation userInformation = new UserInformation(request);
        return userService.getUserGameProfileById(userInformation.getUserId());
    }

    @RequestMapping(value = "/gameRaces/", method = RequestMethod.GET)
    public
    @ResponseBody
    List<RaceAdapter> getGameRaces(ModelMap model, HttpServletRequest request) {

        RaceService raceService = new RaceService();
        return raceService.getAllRaceFull("Ru");
    }

    @RequestMapping(value = "/gameClasses/", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ClassificationAdapter> getGameClasses(ModelMap model, HttpServletRequest request) {

        ClassificationService classificationService = new ClassificationService();
        return classificationService.getAllClassesFull("Ru");
    }

    @RequestMapping(value = "/gameAvatars/", method = RequestMethod.GET)
    public
    @ResponseBody
    List<RacePicture> getAvatar(ModelMap model, HttpServletRequest request) {

        RacePictureService racePictureService = new RacePictureService();
        return racePictureService.getAll();
    }

    @RequestMapping(value = "/checkNickname/{nickname}", method = RequestMethod.POST)
    public
    @ResponseBody
    Boolean getAvatar(ModelMap model, HttpServletRequest request, @PathVariable String nickname) {
        UserService userService = new UserService();
        return userService.checkUserNickname(nickname);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getMainGame(ModelMap model, HttpServletRequest request) {
        return "user/Test";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistration(ModelMap model, HttpServletRequest request) {
        return "user/Registration";
    }
}
