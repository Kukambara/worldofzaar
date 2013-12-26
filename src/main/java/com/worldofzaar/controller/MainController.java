package com.worldofzaar.controller;

import com.worldofzaar.modelAttribute.VKInitialization;
import com.worldofzaar.service.AuthorizationService;
import com.worldofzaar.util.WOZConsts;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class MainController {


    @ModelAttribute("")
    public VKInitialization getApiTable() {
        return new VKInitialization();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model, HttpServletResponse response, HttpServletRequest request, @ModelAttribute("") VKInitialization vk) {
        System.out.println("FROM MAIN");
        if (vk.valid()) {
            AuthorizationService authorizationService = new AuthorizationService();
            authorizationService.login(vk, response, request);
        }
        if (request.getSession().getAttribute(WOZConsts.WEB_USER) == null && request.getSession().getAttribute(WOZConsts.VK_USER) == null)
            return "hello";
        if (request.getSession().getAttribute(WOZConsts.USER_ID) == null &&
                (request.getSession().getAttribute(WOZConsts.WEB_USER) != null || request.getSession().getAttribute(WOZConsts.VK_USER) != null))
            return "redirect:/profile/registration";
        else
            return "redirect:/profile";
    }

    @RequestMapping(value = "adminSignIn", method = RequestMethod.GET)
    public String getAdminSignInPage(ModelMap model) {
        return "admin/signIn";
    }

    @RequestMapping(value = "adminSignUp", method = RequestMethod.GET)
    public String getAdminSignUpPage(ModelMap model) {

        return "admin/signUp";
    }

    @RequestMapping(value = "adminSignIn", method = RequestMethod.POST)
    public String signIn(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                         @RequestParam("email") String email, @RequestParam("password") String pass) {
        AuthorizationService serv = new AuthorizationService();
        if (!serv.login(email, pass, request, response)) {
            model.addAttribute(WOZConsts.SYSTEM_ERROR, "User with this email and the password was not found.");
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "adminSignUp", method = RequestMethod.POST)
    public String signUp(ModelMap model, HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String pass) {
        AuthorizationService serv = new AuthorizationService();

        if (!serv.registration(request, email, pass)) {
            model.addAttribute(WOZConsts.SYSTEM_ERROR, "User with this email already exist.");
        } else {
            model.addAttribute(WOZConsts.SYSTEM_INFO, "Check your email to confirm registration.");
        }

        return "redirect:/adminSignUp";
    }
}