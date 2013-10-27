package com.worldofzaar.controller;

import com.worldofzaar.adapter.AbstractMasterOfDeckAdapter;
import com.worldofzaar.adapter.DeckAdapter;
import com.worldofzaar.adapter.UserCardAdapter;
import com.worldofzaar.entity.*;
import com.worldofzaar.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @RequestMapping(value = "/userDecks/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<DeckAdapter> getUserDecks(ModelMap model, HttpServletRequest request, @PathVariable Integer userId) {
        DeckService deckService = new DeckService();
        List<Deck> decks = deckService.getUserDecksById(userId);
        List<DeckAdapter> deckAdapters = new ArrayList<DeckAdapter>();

        for (Deck tmp : decks) {
            deckAdapters.add(new DeckAdapter(tmp));
        }
        return deckAdapters;
    }

    @RequestMapping(value = "/userCards/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<UserCardAdapter> getUserCards(ModelMap model, HttpServletRequest request, @PathVariable Integer userId) {
        UserCardService userCardService = new UserCardService();
        List<UserCard> userCards = userCardService.getAllUserCardsById(userId);
        List<UserCardAdapter> userCardAdapter = new ArrayList<UserCardAdapter>();

        for (UserCard tmp : userCards) {
            userCardAdapter.add(new UserCardAdapter(tmp));
        }
        return userCardAdapter;
    }

    @RequestMapping(value = "/deckCards/{deckId}", method = RequestMethod.GET)
    public @ResponseBody List<UserCardAdapter> getDeckCards(ModelMap model, HttpServletRequest request, @PathVariable Integer deckId) {
        UserCardService userCardService = new UserCardService();
        List<UserCard> userCards = userCardService.getAllUserCardsById(deckId);
        List<UserCardAdapter> userCardAdapter = new ArrayList<UserCardAdapter>();

        for (UserCard tmp : userCards) {
            userCardAdapter.add(new UserCardAdapter(tmp));
        }
        return userCardAdapter;
    }

    @RequestMapping(value = "/allCards/", method = RequestMethod.GET)
    public @ResponseBody
    AbstractMasterOfDeckAdapter getAllCards(ModelMap model, HttpServletRequest request) {
        WarriorCardService warriorCardService = new WarriorCardService();
        SupportCardService supportCardService = new SupportCardService();
        MasterOfDeckService masterOfDeckService = new MasterOfDeckService();

        List<Object[]> warriorCards = warriorCardService.getCompositeWarriorsCards("Ru");
        List<Object[]> supportCards = supportCardService.getCompositeSupportCards("Ru");
        List<MasterOfDeck> masterOfDeck = masterOfDeckService.getAllPrice();



        AbstractMasterOfDeckAdapter allCardsAdapter = new AbstractMasterOfDeckAdapter();

        return allCardsAdapter;
    }

    @RequestMapping(value = "/gameProfile/", method = RequestMethod.GET)
    public @ResponseBody GameProfile getUserProfile(ModelMap model, HttpServletRequest request, @PathVariable Integer userId) {

        UserService userService = new UserService();
        GameProfile gameProfile =userService.getUserGameProfileById(userId) ;

        return gameProfile;
    }
}
