package com.worldofzaar.controller;

import com.worldofzaar.adapter.TablesAdapter;
import com.worldofzaar.entity.ApiTable;
import com.worldofzaar.service.CertainTableService;
import com.worldofzaar.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.11.13
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/api/")
public class NormalApiController {


    @ModelAttribute("getIn")
    public ApiTable getApiTable() {
        return new ApiTable();
    }

    // /api/tables/getIn?size=4&position=1&cost=0
    @RequestMapping(value = "/tables/getIn", method = RequestMethod.GET)
    public
    @ResponseBody
    Boolean getInTable(HttpServletRequest request, ModelMap model, @ModelAttribute("getIn") ApiTable table) {
        CertainTableService certainTableService = new CertainTableService();
        return certainTableService.getIn(table, request);
    }

    // /api/tables/getOut?size=4&position=1&cost=0
    @RequestMapping(value = "/tables/getOut", method = RequestMethod.GET)
    public
    @ResponseBody
    Boolean getOutTable(HttpServletRequest request, ModelMap model, @ModelAttribute("getIn") ApiTable table) {
        CertainTableService certainTableService = new CertainTableService();
        return certainTableService.getOut(table, request);
    }

    @RequestMapping(value = "/games/isMyGameReady", method = RequestMethod.GET)
    public
    @ResponseBody
    Boolean isMyGameReady(HttpServletRequest request, ModelMap model) {
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

    @RequestMapping(value = "/getTables/cost100", method = RequestMethod.GET)
    public
    @ResponseBody
    TablesAdapter getCost100Tables(ModelMap model, HttpServletRequest request) {
        CertainTableService certainTableService = new CertainTableService();
        return certainTableService.getTables(100, request);
    }


}
