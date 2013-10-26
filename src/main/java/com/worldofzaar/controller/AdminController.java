package com.worldofzaar.controller;

import com.worldofzaar.service.AdminService;
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

    @RequestMapping(value = "/race/createRace", method = RequestMethod.POST)
    public String createRace(ModelMap model, @RequestParam("ruName") String ruName,
                             @RequestParam("ruDescription") String ruDescription,
                             @RequestParam("engName") String engName,
                             @RequestParam("engDescription") String engDescription) {
        return "admin/Race/raceList";
    }


}
