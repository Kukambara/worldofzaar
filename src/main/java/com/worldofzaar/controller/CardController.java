package com.worldofzaar.controller;

import com.worldofzaar.entity.Card;
import com.worldofzaar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 15.11.13
 * Time: 9:45
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/card/")
public class CardController {

    @Autowired
    private ServletContext context;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createCard(ModelMap model) {
        EngClassTextService engClassTextService = new EngClassTextService();
        PropertyService propertyService = new PropertyService();
        SubsetService subsetService = new SubsetService();
        model.addAttribute("classes", engClassTextService.getAllEngClassTexts());
        model.addAttribute("properties", propertyService.getAllProperties());
        model.addAttribute("subsets", subsetService.getList());
        return "admin/Card/createCard";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createCard(MultipartHttpServletRequest request, ModelMap model,
                             @RequestParam("energy") Integer energy, @RequestParam("classId") Integer classId,
                             @RequestParam("propertyId") Integer propertyId, @RequestParam("subsetId") Integer subsetId,
                             @RequestParam("propertyString") String propertyString, @RequestParam("armor") Integer armor,
                             @RequestParam("damage") Integer damage, @RequestParam("health") Integer health,
                             @RequestParam("ruName") String ruName, @RequestParam("engName") String engName,
                             @RequestParam("ruSlogan") String ruSlogan, @RequestParam("engSlogan") String engSlogan) {
        boolean warriorCard = false;
        boolean isElite = (request.getParameter("isElite") != null);

        if (request.getParameter("cardType").equals("warrior"))
            warriorCard = true;
        if (warriorCard) {
            WarriorCardService warriorCardService = new WarriorCardService();
            warriorCardService.addCard(request, context, energy, classId, propertyId, subsetId, propertyString,
                    armor, damage, health, ruName, engName, ruSlogan, engSlogan, isElite);
        } else {

        }


        return "redirect:/admin/card/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listCard(ModelMap model) {
        WarriorCardService warriorCardService = new WarriorCardService();
        SupportCardService supportCardService = new SupportCardService();
        List<Card> cards = new ArrayList<Card>();
        cards.addAll(warriorCardService.getList());
        cards.addAll(supportCardService.getList());
        model.addAttribute("cards", cards);
        return "admin/Card/cardList";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editCard(ModelMap model) {

        return "redirect:/admin/card/editCard";
    }

    @RequestMapping(value = "edit/{cardId}", method = RequestMethod.POST)
    public String editCard(MultipartHttpServletRequest request, ModelMap model, @PathVariable("cardId") Integer setId) {

        return "redirect:/admin/card/list";
    }

}
