package com.worldofzaar.controller;

import com.worldofzaar.service.EngSetTextService;
import com.worldofzaar.service.RuSetTextService;
import com.worldofzaar.service.SetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 13.11.13
 * Time: 0:44
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/set")
public class SetController {

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createSet(ModelMap model) {
        return "admin/Set/createSet";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listSet(ModelMap model) {
        EngSetTextService engSetTextService = new EngSetTextService();
        model.addAttribute("sets", engSetTextService.getList());
        return "admin/Set/setList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createSet(HttpServletRequest request, ModelMap model, @RequestParam("ruName") String ruName,
                            @RequestParam("ruDescription") String ruDescription,
                            @RequestParam("engName") String engName,
                            @RequestParam("engDescription") String engDescription,
                            @RequestParam("date") String date) {
        SetService setService = new SetService();
        setService.createSet(ruName, ruDescription, engName, engDescription, date);
        return "redirect:/admin/set/list";
    }

    @RequestMapping(value = "/edit/{setId}", method = RequestMethod.GET)
    public String editSet(ModelMap model, @PathVariable("setId") Integer setId) {
        EngSetTextService engSetTextService = new EngSetTextService();
        RuSetTextService ruSetTextService = new RuSetTextService();
        model.addAttribute("engText", engSetTextService.getTextBySetId(setId));
        model.addAttribute("ruText", ruSetTextService.getTextBySetId(setId));

        return "admin/Set/EditSet";
    }

    @RequestMapping(value = "/edit/{setId}", method = RequestMethod.POST)
    public String editSet(HttpServletRequest request, ModelMap model, @RequestParam("ruName") String ruName,
                          @RequestParam("ruDescription") String ruDescription,
                          @RequestParam("engName") String engName,
                          @RequestParam("engDescription") String engDescription,
                          @RequestParam("date") String date,
                          @PathVariable("setId") Integer setId) {
        SetService setService = new SetService();
        setService.editSet(setId, ruName, ruDescription, engName, engDescription, date);
        return "redirect:/admin/set/list";
    }

    @RequestMapping(value = "/delete/{setId}", method = RequestMethod.GET)
    public String deleteSet(ModelMap model, @PathVariable("setId") Integer setId) {
        EngSetTextService engSetTextService = new EngSetTextService();
        SetService setService = new SetService();
        setService.deleteSet(setId);
        return "redirect:/admin/set/list";
    }
}
