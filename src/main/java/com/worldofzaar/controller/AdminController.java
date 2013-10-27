package com.worldofzaar.controller;

import com.worldofzaar.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAdminPage(ModelMap model) {
        return "admin/adminPage";
    }

    @RequestMapping(value = "/adminList", method = RequestMethod.GET)
    public String getAdminList(ModelMap model) {
        AdminService adminService = new AdminService();
        model.addAttribute("admins", adminService.getAdminList());
        return "admin/adminList";
    }

    @RequestMapping(value = "/approveAdmin/{id}", method = RequestMethod.GET)
    public String approveAdmin(ModelMap model, @PathVariable("id") Integer id) {
        AdminService adminService = new AdminService();
        adminService.approveAdmin(id);
        return "redirect:/admin/adminList";
    }

    @RequestMapping(value = "/disapproveAdmin/{id}", method = RequestMethod.GET)
    public String disapproveAdmin(ModelMap model, @PathVariable("id") Integer id) {
        AdminService adminService = new AdminService();
        adminService.disapproveAdmin(id);
        return "redirect:/admin/adminList";
    }

    @RequestMapping(value = "/deleteAdmin/{id}", method = RequestMethod.GET)
    public String deleteAdmin(ModelMap model, @PathVariable("id") Integer id) {
        AdminService adminService = new AdminService();
        adminService.deleteAdmin(id);
        return "redirect:/admin/adminList";
    }

    @RequestMapping(value = "/race/createRace", method = RequestMethod.GET)
    public String createRace(ModelMap model) {
        return "admin/Race/createRace";
    }

    @RequestMapping(value = "/race/raceList", method = RequestMethod.GET)
    public String raceList(ModelMap model) {
        EngRaceTextService engRaceTextService = new EngRaceTextService();
        model.addAttribute("races", engRaceTextService.getAllEngRaceTexts());
        return "admin/Race/raceList";
    }

    @RequestMapping(value = "/race/createRace", method = RequestMethod.POST)
    public String createRace(ModelMap model, @RequestParam("ruName") String ruName,
                             @RequestParam("ruDescription") String ruDescription,
                             @RequestParam("engName") String engName,
                             @RequestParam("engDescription") String engDescription) {
        RaceService raceService = new RaceService();
        raceService.createRace(ruName, ruDescription, engName, engDescription);
        return "redirect:/admin/race/raceList";
    }

    @RequestMapping(value = "/class/classList", method = RequestMethod.GET)
    public String classList(ModelMap model) {
        EngClassTextService engClassTextService = new EngClassTextService();
        model.addAttribute("classes", engClassTextService.getAllEngClassTexts());
        return "admin/Class/classList";
    }

    @RequestMapping(value = "/class/createClass", method = RequestMethod.GET)
    public String createClass(ModelMap model) {
        EngRaceTextService engRaceTextService = new EngRaceTextService();
        model.addAttribute("races", engRaceTextService.getAllEngRaceTexts());
        return "admin/Class/createClass";
    }

    @RequestMapping(value = "/class/createClass", method = RequestMethod.POST)
    public String createClass(ModelMap model, @RequestParam("ruName") String ruName,
                              @RequestParam("ruDescription") String ruDescription,
                              @RequestParam("engName") String engName,
                              @RequestParam("engDescription") String engDescription,
                              @RequestParam("raceId") String raceId) {
        ClassificationService classificationService = new ClassificationService();
        classificationService.createClass(ruName, ruDescription, engName, engDescription, raceId);
        return "redirect:/admin";
    }


}
