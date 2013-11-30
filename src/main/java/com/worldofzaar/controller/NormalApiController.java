package com.worldofzaar.controller;

import com.worldofzaar.entity.ApiTable;
import com.worldofzaar.service.CertainTableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String getInTable(HttpServletRequest request, ModelMap model, @ModelAttribute("getIn") ApiTable table) {
        CertainTableService certainTableService = new CertainTableService();
        certainTableService.getIn(table, request);
        return "";
    }

    // /api/tables/getIn?size=4&position=1&cost=0
    @RequestMapping(value = "/tables/getOut", method = RequestMethod.GET)
    public String getOutTable(HttpServletRequest request, ModelMap model, @ModelAttribute("getIn") ApiTable table) {
        CertainTableService certainTableService = new CertainTableService();
        certainTableService.getOut(table, request);
        return "";
    }

    @RequestMapping(value = "/getTables/free", method = RequestMethod.GET)
    public String getFreeTables(ModelMap model) {
        return "";
    }

    @RequestMapping(value = "/getTables/cost10", method = RequestMethod.GET)
    public String getCost10Tables(ModelMap model) {
        return "";
    }

    @RequestMapping(value = "/getTables/cost50", method = RequestMethod.GET)
    public String getCost50Tables(ModelMap model) {
        return "";
    }

    @RequestMapping(value = "/getTables/cost100", method = RequestMethod.GET)
    public String getCost100Tables(ModelMap model) {
        return "";
    }


}
