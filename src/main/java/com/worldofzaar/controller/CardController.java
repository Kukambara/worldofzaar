package com.worldofzaar.controller;

import com.worldofzaar.entity.EngCardText;
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
                             @RequestParam("ruSlogan") String ruSlogan, @RequestParam("engSlogan") String engSlogan,
                             @RequestParam("ruProperty") String ruProperty, @RequestParam("engProperty") String engProperty) {

        boolean isElite = (request.getParameter("isElite") != null);
        CardService cardService = new CardService();
        cardService.createCard(request, context, energy, classId, propertyId, subsetId, propertyString,
                armor, damage, health, ruName, engName, ruSlogan, engSlogan, ruProperty, engProperty, isElite);

        return "redirect:/admin/card/list";
    }

    /* MasterOfDeckService masterOfDeckService = new MasterOfDeckService();
        for (EngCardText cardText : cardTexts) {
            Integer cardId;
            if (cardText.getSupportCard() != null) {
                cardId = cardText.getSupportCard().getCardId();
                masterOfDeckService.createSuppMasterOfDeck(cardId, -1, 0, 0);
            } else {
                cardId = cardText.getWarriorCard().getCardId();
                masterOfDeckService.createWarrMasterOfDeck(cardId, -1, 0, 0);
            }
        }*/

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listCard(ModelMap model) {
        EngCardTextService engCardTextService = new EngCardTextService();
        List<EngCardText> cardTexts = engCardTextService.getList();
        model.addAttribute("cards", cardTexts);
        return "admin/Card/cardList";
    }

    @RequestMapping(value = "edit/{cardId}", method = RequestMethod.GET)
    public String editCard(ModelMap model, @PathVariable("cardId") Integer cardId) {
        EngClassTextService engClassTextService = new EngClassTextService();
        PropertyService propertyService = new PropertyService();
        SubsetService subsetService = new SubsetService();
        EngCardTextService engCardTextService = new EngCardTextService();
        RuCardTextService ruCardTextService = new RuCardTextService();
        EngPropertyTextService engPropertyTextService = new EngPropertyTextService();
        RuPropertyTextService ruPropertyTextService = new RuPropertyTextService();
        model.addAttribute("classes", engClassTextService.getAllEngClassTexts());
        model.addAttribute("properties", propertyService.getAllProperties());
        model.addAttribute("subsets", subsetService.getList());
        model.addAttribute("engCardText", engCardTextService.getText(cardId));
        model.addAttribute("ruCardText", ruCardTextService.getText(cardId));
        model.addAttribute("engPropertyText", engPropertyTextService.getText(cardId));
        model.addAttribute("ruPropertyText", ruPropertyTextService.getText(cardId));
        model.addAttribute("cardId", cardId);

        return "admin/Card/editCard";
    }

    @RequestMapping(value = "edit/{cardId}", method = RequestMethod.POST)
    public String editCard(MultipartHttpServletRequest request, ModelMap model, @PathVariable("cardId") Integer
            cardId,
                           @RequestParam("energy") Integer energy, @RequestParam("classId") Integer classId,
                           @RequestParam("propertyId") Integer propertyId, @RequestParam("subsetId") Integer subsetId,
                           @RequestParam("propertyString") String propertyString, @RequestParam("armor") Integer armor,
                           @RequestParam("damage") Integer damage, @RequestParam("health") Integer health,
                           @RequestParam("ruName") String ruName, @RequestParam("engName") String engName,
                           @RequestParam("ruSlogan") String ruSlogan, @RequestParam("engSlogan") String engSlogan,
                           @RequestParam("ruProperty") String ruProperty, @RequestParam("engProperty") String engProperty) {

        boolean isElite = (request.getParameter("isElite") != null);
        CardService cardService = new CardService();
        cardService.editCard(cardId, request, context, energy, classId, propertyId, subsetId, propertyString,
                armor, damage, health, ruName, engName, ruSlogan, engSlogan, ruProperty, engProperty, isElite);


        return "redirect:/admin/card/list";
    }

    @RequestMapping(value = "delete/{cardId}", method = RequestMethod.GET)
    public String deleteCard(ModelMap model, @PathVariable("cardId") Integer cardId) {
        CardService cardService = new CardService();
        cardService.deleteCard(cardId);
        return "redirect:/admin/card/list";
    }

}
