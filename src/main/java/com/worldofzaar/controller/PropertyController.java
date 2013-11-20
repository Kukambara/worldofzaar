package com.worldofzaar.controller;

import com.worldofzaar.service.PropertyService;
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
 * Date: 18.11.13
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/property")
public class PropertyController {
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProperty(ModelMap model) {
        return "admin/Property/createProperty";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listSet(ModelMap model) {
        PropertyService propertyService = new PropertyService();
        propertyService.getAllProperties();
        model.addAttribute("properties", propertyService.getAllProperties());
        return "admin/Property/listProperty";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createSet(HttpServletRequest request, ModelMap model, @RequestParam("realization") String realization,
                            @RequestParam("description") String description) {
        PropertyService propertyService = new PropertyService();
        propertyService.addProperty(realization, description);
        return "redirect:/admin/property/list";
    }

    @RequestMapping(value = "/edit/{propertyId}", method = RequestMethod.GET)
    public String editSet(ModelMap model, @PathVariable("propertyId") Integer propertyId) {
        PropertyService propertyService = new PropertyService();
        model.addAttribute("property", propertyService.getProperty(propertyId));
        return "admin/Property/editProperty";
    }

    @RequestMapping(value = "/edit/{propertyId}", method = RequestMethod.POST)
    public String editSet(HttpServletRequest request, ModelMap model, @RequestParam("realization") String realization,
                          @RequestParam("description") String description,
                          @PathVariable("propertyId") Integer propertyId) {
        PropertyService propertyService = new PropertyService();
        propertyService.updateProperty(propertyId, realization, description);
        return "redirect:/admin/property/edit/" + propertyId;
    }

    @RequestMapping(value = "/delete/{propertyId}", method = RequestMethod.GET)
    public String deleteSet(ModelMap model, @PathVariable("propertyId") Integer propertyId) {
        PropertyService propertyService = new PropertyService();
        propertyService.deleteProperty(propertyId);
        return "redirect:/admin/property/list";
    }
}
