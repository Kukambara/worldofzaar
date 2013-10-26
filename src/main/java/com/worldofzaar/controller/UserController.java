package com.worldofzaar.controller;

import com.worldofzaar.entity.Deck;
import com.worldofzaar.service.DeckService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/decks/{userId}", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Deck> signIn(ModelMap model, HttpServletRequest request, @PathVariable Integer userId) {
        DeckService deckService = new DeckService();
        return deckService.getUserDecksById(userId);

    }
}
