package com.worldofzaar.controller;

import com.worldofzaar.service.ExperienceService;
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
 * Time: 3:50
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/experience/")
public class ExperienceController {

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createExperience(ModelMap model) {
        return "admin/Experience/createExperience";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createExperience(ModelMap model, @RequestParam("level") Integer level,
                                   @RequestParam("experience") Integer experience) {
        ExperienceService experienceService = new ExperienceService();
        experienceService.addExperience(level, experience);
        return "redirect:/admin/experience/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listExperience(ModelMap model) {
        ExperienceService experienceService = new ExperienceService();
        model.addAttribute("experiences", experienceService.getList());
        return "admin/Experience/listExperience";
    }

    @RequestMapping(value = "edit/{expId}", method = RequestMethod.GET)
    public String editExperience(ModelMap model, @PathVariable("expId") Integer expId) {
        ExperienceService experienceService = new ExperienceService();
        model.addAttribute("experience", experienceService.getExperience(expId));
        return "admin/Experience/editExperience";
    }

    @RequestMapping(value = "edit/{expId}", method = RequestMethod.POST)
    public String editExperience(ModelMap model, @PathVariable("expId") Integer expId,
                                 @RequestParam("level") Integer level, @RequestParam("experience") Integer experience) {
        ExperienceService experienceService = new ExperienceService();
        experienceService.editExperience(expId, level, experience);
        return "redirect:/admin/experience/list";
    }

    @RequestMapping(value = "delete/{expId}", method = RequestMethod.GET)
    public String deleteExperience(ModelMap model, @PathVariable("expId") Integer expId) {
        ExperienceService experienceService = new ExperienceService();
        experienceService.deleteExperience(expId);
        return "redirect:/admin/experience/list";
    }


}
