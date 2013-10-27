package com.worldofzaar.controller;

import com.worldofzaar.service.AuthorizationService;
import com.worldofzaar.util.WOZConsts;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.07.13
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class AuthorizationController {

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(ModelMap model, HttpServletResponse response, HttpServletRequest request,
                         @RequestParam("email") String email, @RequestParam("password") String pass) {
        AuthorizationService serv = new AuthorizationService();
        if (!serv.login(email, pass, request, response)) {
            model.addAttribute(WOZConsts.SYSTEM_ERROR, "User with this email and the password was not found.");
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(ModelMap model, HttpServletResponse response, HttpServletRequest request) {
        AuthorizationService serv = new AuthorizationService();
        serv.logout(request, response);
        return "redirect:/";
    }

    @RequestMapping(value = "/confirm/{email}/{md5}", method = RequestMethod.GET)
    public String confirmAccount(ModelMap model, HttpServletResponse response, HttpServletRequest request,
                                 @PathVariable String email, @PathVariable String md5) {
        AuthorizationService serv = new AuthorizationService();
        if (serv.confirmAccount(email, md5, request, response)) {
            model.addAttribute(WOZConsts.SYSTEM_INFO, "Your account has confirmed successfully.");
        } else
            model.addAttribute(WOZConsts.SYSTEM_ERROR, "Something wrong with confirmation.");
        return "redirect:/";
    }

    @RequestMapping(value = "/confirm/{email}/{md5}/zaaradmin", method = RequestMethod.GET)
    public String setAdminAccount(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                                  @PathVariable String email, @PathVariable String md5) {
        AuthorizationService serv = new AuthorizationService();
        if (serv.setAdminAccount(email, md5, request, response)) {
            model.addAttribute(WOZConsts.SYSTEM_INFO, "Your account has added to Admin list");
        } else
            model.addAttribute(WOZConsts.SYSTEM_ERROR, "Something wrong with confirmation.");
        return "redirect:/admin";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String signUp(ModelMap model) {
        return "authorization/signup";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(ModelMap model, HttpServletRequest request, @RequestParam("email") String email, @RequestParam("pass") String pass) {
        AuthorizationService serv = new AuthorizationService();

        if (!serv.registration(request, email, pass)) {
            model.addAttribute(WOZConsts.SYSTEM_ERROR, "User with this email already exist.");
        } else {
            model.addAttribute(WOZConsts.SYSTEM_INFO, "Check your email to confirm registration.");
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/restoreAccount", method = RequestMethod.POST)
    public String restoreAccount(ModelMap model, @RequestParam("email") String email) {
        AuthorizationService serv = new AuthorizationService();
        if (serv.restoreAccount(email)) {
            model.addAttribute(WOZConsts.SYSTEM_INFO, "Letter with new password has sent to your email.");
        } else {
            model.addAttribute(WOZConsts.SYSTEM_ERROR, "Email does not exist!");
            return "redirect:/restoreAccount";
        }

        return "redirect:/admin";
    }

    @RequestMapping(value = "/restoreAccount", method = RequestMethod.GET)
    public String restoreAccount(ModelMap model) {
        return "admin/restoreAccount";
    }
}
