package com.worldofzaar.controller;

import com.worldofzaar.adapter.GameAdapter;
import com.worldofzaar.adapter.TablesAdapter;
import com.worldofzaar.modelAttribute.ApiTable;
import com.worldofzaar.modelAttribute.Card;
import com.worldofzaar.modelAttribute.CardPosition;
import com.worldofzaar.modelAttribute.Move;
import com.worldofzaar.service.CertainTableService;
import com.worldofzaar.service.GameAdapterService;
import com.worldofzaar.service.GameService;
import com.worldofzaar.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.11.13
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/api")
public class NormalApiController {

    @Autowired
    private ServletContext context;

    @ModelAttribute("getIn")
    public ApiTable getApiTable() {
        return new ApiTable();
    }

    @ModelAttribute("move")
    public Move getMove() {
        return new Move();
    }

    @ModelAttribute("throwOff")
    public Card getCard() {
        return new Card();
    }

    // /api/tables/getIn?size=4&position=1&cost=0
    @RequestMapping(value = "/tables/getIn", method = RequestMethod.GET)
    public
    @ResponseBody
    Boolean getInTable(HttpServletRequest request, ModelMap model, @ModelAttribute("getIn") ApiTable table) {
        CertainTableService certainTableService = new CertainTableService();
        return certainTableService.getIn(table, request);
    }

    @RequestMapping(value = "/tables/getOut", method = RequestMethod.GET)
    public
    @ResponseBody
    Boolean getOutTable(HttpServletRequest request, ModelMap model) {
        CertainTableService certainTableService = new CertainTableService();
        return certainTableService.getOut(request);
    }

    @RequestMapping(value = "/game/isGameCreated", method = RequestMethod.GET)
    public
    @ResponseBody
    Boolean isGameCreated(HttpServletRequest request, ModelMap model) {
        GameService gameService = new GameService();
        return gameService.isGameCreated(request);
    }

    @RequestMapping(value = "/tables/getTables/free", method = RequestMethod.GET)
    public
    @ResponseBody
    TablesAdapter getFreeTables(ModelMap model, HttpServletRequest request) {
        CertainTableService certainTableService = new CertainTableService();
        return certainTableService.getTables(0, request);
    }

    @RequestMapping(value = "/tables/getTables/cost10", method = RequestMethod.GET)
    public
    @ResponseBody
    TablesAdapter getCost10Tables(ModelMap model, HttpServletRequest request) {
        CertainTableService certainTableService = new CertainTableService();
        return certainTableService.getTables(10, request);
    }

    @RequestMapping(value = "/tables/getTables/cost50", method = RequestMethod.GET)
    public
    @ResponseBody
    TablesAdapter getCost50Tables(ModelMap model, HttpServletRequest request) {
        CertainTableService certainTableService = new CertainTableService();
        return certainTableService.getTables(50, request);
    }

    @RequestMapping(value = "/tables/getTables/cost100", method = RequestMethod.GET)
    public
    @ResponseBody
    TablesAdapter getCost100Tables(ModelMap model, HttpServletRequest request) {
        CertainTableService certainTableService = new CertainTableService();
        return certainTableService.getTables(100, request);
    }

    @RequestMapping(value = "/game/getGame", method = RequestMethod.GET)
    public
    @ResponseBody
    GameAdapter getGame(ModelMap model, HttpServletRequest request) {
        GameAdapterService gameAdapterService = new GameAdapterService();
        return gameAdapterService.getGameAdapter(request);
    }

    @RequestMapping(value = "/game/isGameReady", method = RequestMethod.GET)
    public
    @ResponseBody
    Boolean isGameReady(ModelMap model, HttpServletRequest request) {
        GameService gameService = new GameService();
        return gameService.isGameReady(request);
    }

    @RequestMapping(value = "/game/userIsReady", method = RequestMethod.GET)
    public void userIsReady(ModelMap model, HttpServletRequest request) {
        HeroService heroService = new HeroService();
        heroService.heroIsReady(request);
    }

    @RequestMapping(value = "/game/move/endMove", method = RequestMethod.GET)
    public void endMove(ModelMap model, HttpServletRequest request) {
        GameService gameService = new GameService();
        gameService.endMove(request);
    }

    /**
     * /api/game/move?myCardId=1&enemyId=2&enemyCardId=3
     * enemyCardId - not required.
     *
     * @param request
     * @param move
     */
    @RequestMapping(value = "/game/move", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void move(HttpServletRequest request, @ModelAttribute("move") Move move) {
        GameService gameService = new GameService();
        //Method move(HttpServletRequest, Move) return boolean value. Probably we should return boolean value rather then only HttpStatus.OK.
        gameService.move(request, move);
    }

    @RequestMapping(value = "/game/putCard", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void putCard(ModelMap model, HttpServletRequest request, @ModelAttribute("putCard") CardPosition cardPosition) {
        GameService gameService = new GameService();
        gameService.putCard(request, cardPosition);
    }

    // /api/game/throwOff&cardId=1
    @RequestMapping(value = "/game/throwOff", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void throwOff(ModelMap model, HttpServletRequest request, @ModelAttribute("throwOff") Card card) {
        GameService gameService = new GameService();
        //Method throwOff(HttpServletRequest, Move) return boolean value. Probably we should return boolean value rather then only HttpStatus.OK.
        gameService.throwOff(request, card);
    }

    @RequestMapping(value = "/game/skip", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void skip(ModelMap model, HttpServletRequest request) {
        GameService gameService = new GameService();
        gameService.skipMove(request);
    }


}