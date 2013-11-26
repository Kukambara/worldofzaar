package com.worldofzaar.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.11.13
 * Time: 3:05
 * To change this template use File | Settings | File Templates.
 */
public class CardService {
    public void deleteCard(Integer cardId) {
        SupportCardService supportCardService = new SupportCardService();
        WarriorCardService warriorCardService = new WarriorCardService();
        EngCardTextService engCardTextService = new EngCardTextService();
        RuCardTextService ruCardTextService = new RuCardTextService();
        EngPropertyTextService engPropertyTextService = new EngPropertyTextService();
        RuPropertyTextService ruPropertyTextService = new RuPropertyTextService();


        engCardTextService.deleteTextByCardId(cardId);
        ruCardTextService.deleteTextByCardId(cardId);
        engPropertyTextService.deleteTextByCardId(cardId);
        ruPropertyTextService.deleteTextByCardId(cardId);

        supportCardService.deleteCard(cardId);
        warriorCardService.deleteCard(cardId);
    }

    public void createCard(MultipartHttpServletRequest request, ServletContext context, Integer energy, Integer classId,
                           Integer propertyId, Integer subsetId,
                           String propertyString, Integer armor, Integer damage, Integer health,
                           String ruName, String engName, String ruSlogan, String engSlogan, String ruProperty, String engProperty,
                           Boolean isElite) {
        if (request.getParameter("cardType").equals("warrior")) {

            WarriorCardService warriorCardService = new WarriorCardService();
            warriorCardService.addCard(request, context, energy, classId, propertyId, subsetId, propertyString,
                    armor, damage, health, ruName, engName, ruSlogan, engSlogan, ruProperty, engProperty, isElite);
        } else {
            SupportCardService supportCardService = new SupportCardService();
            supportCardService.addCard(request, context, energy, classId, propertyId, subsetId, propertyString,
                    ruName, engName, ruSlogan, engSlogan, ruProperty, engProperty, isElite);
        }
    }

    public void editCard(Integer cardId, MultipartHttpServletRequest request, ServletContext context, Integer energy,
                         Integer classId, Integer propertyId, Integer subsetId,
                         String propertyString, Integer armor, Integer damage, Integer health,
                         String ruName, String engName, String ruSlogan, String engSlogan, String ruProperty, String engProperty,
                         Boolean isElite) {
        if (request.getParameter("cardType").equals("warrior")) {
            WarriorCardService warriorCardService = new WarriorCardService();
            warriorCardService.editCard(cardId, request, context, energy, classId, propertyId, subsetId, propertyString,
                    armor, damage, health, ruName, engName, ruSlogan, engSlogan, ruProperty, engProperty, isElite);
        } else {
            SupportCardService supportCardService = new SupportCardService();
            supportCardService.editCard(cardId, request, context, energy, classId, propertyId, subsetId, propertyString,
                    ruName, engName, ruSlogan, engSlogan, ruProperty, engProperty, isElite);
        }

    }
}
