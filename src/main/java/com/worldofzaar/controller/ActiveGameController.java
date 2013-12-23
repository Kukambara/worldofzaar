package com.worldofzaar.controller;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.12.13
 * Time: 23:57
 * To change this template use File | Settings | File Templates.
 */
@org.springframework.stereotype.Controller
@RequestMapping("/game")
public class ActiveGameController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getGame(ModelMap model) {
        return "game/GameState";
    }
}
