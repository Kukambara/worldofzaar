package com.worldofzaar.controller;

import com.worldofzaar.service.EngSetTextService;
import com.worldofzaar.service.SubsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 14.11.13
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/subset/")
public class SubsetController {

    @Autowired
    private ServletContext context;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String createSubset(ModelMap model) {
        EngSetTextService engSetTextService = new EngSetTextService();
        SubsetService subsetService = new SubsetService();
        model.addAttribute("subsets", subsetService.getList());
        model.addAttribute("sets", engSetTextService.getList());

        return "admin/Set/Subset/createSubset";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String createSubset(MultipartHttpServletRequest request, ModelMap model, @RequestParam("setId") Integer setId,
                               @RequestParam("techName") String techName) {
        SubsetService subsetService = new SubsetService();
        subsetService.addSubset(request, context, setId, techName);
        return "redirect:/admin/subset/add";
    }

    @RequestMapping(value = "edit/{subsetId}", method = RequestMethod.POST)
    public String changeSet(MultipartHttpServletRequest request, ModelMap model, @RequestParam("setId") Integer setId,
                            @PathVariable("subsetId") Integer subsetId, @RequestParam("techName") String techName) {
        SubsetService subsetService = new SubsetService();
        subsetService.editSubset(request, context, subsetId, setId, techName);
        return "redirect:/admin/subset/add";
    }

    @RequestMapping(value = "delete/{subsetId}", method = RequestMethod.GET)
    public String createSubset(ModelMap model, @PathVariable("subsetId") Integer subsetId) {
        SubsetService subsetService = new SubsetService();
        subsetService.deleteSubset(subsetId);
        return "redirect:/admin/subset/add";
    }
}
