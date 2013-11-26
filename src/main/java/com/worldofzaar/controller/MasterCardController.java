package com.worldofzaar.controller;

import com.worldofzaar.service.EngCardTextService;
import com.worldofzaar.service.MasterOfDeckService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.11.13
 * Time: 19:57
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/mastercard/")
public class MasterCardController {
    /*
    Create methods depricated now
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createMasterCard(ModelMap model) {
        EngCardTextService engCardTextService = new EngCardTextService();
        model.addAttribute("cards", engCardTextService.getList());
        return "admin/MasterCard/createMaster";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createMasterCard(ModelMap model, @RequestParam("cardLevel") Integer cardLevel,
                                   @RequestParam("price") Integer price, @RequestParam("donatePrice") Integer donatePrice,
                                   @RequestParam("cardId") Integer cardId) {
        MasterOfDeckService masterOfDeckService = new MasterOfDeckService();
        masterOfDeckService.createMasterOfDeck(cardId, cardLevel, price, donatePrice);
        return "redirect:/admin/mastercard/list";
    }
      */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listMasterCard(ModelMap model) {
        MasterOfDeckService masterOfDeckService = new MasterOfDeckService();
        model.addAttribute("masterCards", masterOfDeckService.getList());
        return "admin/MasterCard/listMaster";
    }

    @RequestMapping(value = "edit/{masterId}", method = RequestMethod.GET)
    public String editMasterCard(ModelMap model, @PathVariable("masterId") Integer masterId) {
        EngCardTextService engCardTextService = new EngCardTextService();
        model.addAttribute("cards", engCardTextService.getList());
        MasterOfDeckService masterOfDeckService = new MasterOfDeckService();
        model.addAttribute("masterCard", masterOfDeckService.getMasterOfDeck(masterId));
        return "admin/MasterCard/editMaster";
    }

    @RequestMapping(value = "edit/{masterId}", method = RequestMethod.POST)
    public String editMasterCard(ModelMap model, @PathVariable("masterId") Integer masterId,
                                 @RequestParam("cardLevel") Integer cardLevel, @RequestParam("price") Integer price,
                                 @RequestParam("donatePrice") Integer donatePrice, @RequestParam("cardId") Integer cardId) {
        MasterOfDeckService masterOfDeckService = new MasterOfDeckService();
        masterOfDeckService.editMasterOfDeck(masterId, cardId, cardLevel, price, donatePrice);
        return "redirect:/admin/mastercard/list";
    }

    @RequestMapping(value = "delete/{masterId}", method = RequestMethod.GET)
    public String deleteMasterCard(ModelMap model, @PathVariable("masterId") Integer masterId) {
        MasterOfDeckService masterOfDeckService = new MasterOfDeckService();
        masterOfDeckService.deleteMasterOfDeck(masterId);
        return "redirect:/admin/mastercard/list";
    }

}
