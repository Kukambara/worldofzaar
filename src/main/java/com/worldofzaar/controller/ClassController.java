package com.worldofzaar.controller;

import com.worldofzaar.service.*;
import com.worldofzaar.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 06.11.13
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/class")
public class ClassController {

    @Autowired
    private ServletContext context;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String classList(ModelMap model) {
        EngClassTextService engClassTextService = new EngClassTextService();
        model.addAttribute("classes", engClassTextService.getAllEngClassTexts());
        return "admin/Class/classList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createClass(ModelMap model) {
        EngRaceTextService engRaceTextService = new EngRaceTextService();
        model.addAttribute("races", engRaceTextService.getAllEngRaceTexts());
        return "admin/Class/createClass";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createClass(MultipartHttpServletRequest request, ModelMap model,
                              @RequestParam("ruName") String ruName,
                              @RequestParam("ruDescription") String ruDescription,
                              @RequestParam("engName") String engName,
                              @RequestParam("engDescription") String engDescription,
                              @RequestParam("raceId") String raceId) {
        FileManager fileManager = new FileManager();
        String ruPicture = fileManager.upload(request.getFile("ruPicture"), context);
        String engPicture = fileManager.upload(request.getFile("engPicture"), context);
        ClassificationService classificationService = new ClassificationService();
        classificationService.createClass(ruName, ruDescription, engName, engDescription, raceId, ruPicture, engPicture);
        return "redirect:/admin/class/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editClass(ModelMap model, @PathVariable("id") Integer id) {
        EngRaceTextService engRaceTextService = new EngRaceTextService();
        EngClassTextService engClassTextService = new EngClassTextService();
        RuClassTextService ruClassTextService = new RuClassTextService();
        model.addAttribute("races", engRaceTextService.getAllEngRaceTexts());
        model.addAttribute("engClass", engClassTextService.getTextByClassId(id));
        model.addAttribute("ruClass", ruClassTextService.getTextByClassId(id));

        return "admin/Class/editClass";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editClass(MultipartHttpServletRequest request, ModelMap model,
                            @RequestParam("ruName") String ruName,
                            @RequestParam("ruDescription") String ruDescription,
                            @RequestParam("engName") String engName,
                            @RequestParam("engDescription") String engDescription,
                            @RequestParam("raceId") String raceId,
                            @PathVariable("id") Integer id) {
        ClassificationService classificationService = new ClassificationService();
        FileManager fileManager = new FileManager();
        //Get MultipartFiles from request.
        MultipartFile ruPictureMF = request.getFile("ruPicture");
        MultipartFile engPictureMF = request.getFile("engPicture");
        String engPicture = "";
        String ruPicture = "";

        if (engPictureMF.getSize() != 0l)
            engPicture = fileManager.upload(engPictureMF, context);

        if (ruPictureMF.getSize() != 0l)
            ruPicture = fileManager.upload(ruPictureMF, context);

        classificationService.editClass(id, ruName, ruDescription, engName, engDescription, raceId, ruPicture, engPicture);
        return "redirect:/admin/class/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteClass(ModelMap model, @PathVariable("id") Integer id) {
        ClassificationService classificationService = new ClassificationService();
        classificationService.deleteClass(id);
        return "redirect:/admin/class/list";
    }

    @RequestMapping(value = "/cloth/edit", method = RequestMethod.GET)
    public String createCloth(ModelMap model) {
        ClothService clothService = new ClothService();
        model.addAttribute("clothes", clothService.getAllClothes());
        return "admin/Class/Cloth/editClothes";
    }

    @RequestMapping(value = "/cloth/edit", method = RequestMethod.POST)
    public String createCloth(MultipartHttpServletRequest request, ModelMap model) {
        ClothService clothService = new ClothService();
        clothService.addClothes(request, context);
        return "redirect:/admin/class/cloth/edit";
    }

    @RequestMapping(value = "/cloth/edit/{clothId}", method = RequestMethod.POST)
    public String editCloth(MultipartHttpServletRequest request, ModelMap model, @PathVariable("clothId") Integer clothId) {
        ClothService clothService = new ClothService();
        clothService.editCloth(request, context, clothId);
        return "redirect:/admin/class/cloth/edit";
    }

    @RequestMapping(value = "/cloth/delete/{clothId}", method = RequestMethod.GET)
    public String deleteCloth(ModelMap model, @PathVariable("clothId") Integer clothId) {
        ClothService clothService = new ClothService();
        clothService.deleteCloth(clothId);
        return "redirect:/admin/class/cloth/edit";
    }

    @RequestMapping(value = "/blazon/edit", method = RequestMethod.GET)
    public String ListBlazon(ModelMap model) {
        ClothService clothService = new ClothService();
        model.addAttribute("clothes", clothService.getAllClothes());
        return "admin/Class/Blazon/clothList";
    }

    @RequestMapping(value = "/blazon/edit/{clothId}", method = RequestMethod.GET)
    public String createBlazon(ModelMap model, @PathVariable("clothId") Integer clothId) {
        ClothService clothService = new ClothService();
        ClassificationService classificationService = new ClassificationService();
        EngClassTextService engClassTextService = new EngClassTextService();
        BlazonService blazonService = new BlazonService();
        model.addAttribute("cloth", clothService.getCloth(clothId));
        model.addAttribute("blazons", blazonService.getBlazonsByClothId(clothId));
        model.addAttribute("classes", engClassTextService.getAllEngClassTexts());

        return "admin/Class/Blazon/editBlazon";
    }

    @RequestMapping(value = "/blazon/add/{clothId}", method = RequestMethod.POST)
    public String createBlazon(MultipartHttpServletRequest request, ModelMap model,
                               @PathVariable("clothId") Integer clothId, @RequestParam("classId") String classId) {
        BlazonService blazonService = new BlazonService();
        blazonService.addBlazons(request, context, clothId, Integer.valueOf(classId));
        return "redirect:/admin/class/blazon/edit/" + clothId;
    }

    @RequestMapping(value = "/blazon/edit/{blazonId}", method = RequestMethod.POST)
    public String changeClass(MultipartHttpServletRequest request, ModelMap model,
                              @PathVariable("blazonId") Integer blazonId, @RequestParam("classId") String classId) {
        BlazonService blazonService = new BlazonService();
        Integer clothId = blazonService.changeClass(request, context, blazonId, Integer.valueOf(classId));
        return "redirect:/admin/class/blazon/edit/" + clothId;
    }

    @RequestMapping(value = "/blazon/delete/{blazonId}", method = RequestMethod.GET)
    public String deleteBlazon(ModelMap model, @PathVariable("blazonId") Integer blazonId) {
        BlazonService blazonService = new BlazonService();
        Integer clothId = blazonService.getClothIdByBlazonId(blazonId);
        blazonService.deleteBlazon(blazonId);
        return "redirect:/admin/class/blazon/edit/" + clothId;
    }
}
