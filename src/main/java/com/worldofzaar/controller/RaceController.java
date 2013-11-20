package com.worldofzaar.controller;

import com.worldofzaar.entity.EngRaceText;
import com.worldofzaar.entity.RuRaceText;
import com.worldofzaar.service.EngRaceTextService;
import com.worldofzaar.service.RacePictureService;
import com.worldofzaar.service.RaceService;
import com.worldofzaar.service.RuRaceTextService;
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
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/race")
public class RaceController {
    @Autowired
    private ServletContext context;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createRace(ModelMap model) {
        return "admin/Race/createRace";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String raceList(ModelMap model) {
        EngRaceTextService engRaceTextService = new EngRaceTextService();
        model.addAttribute("races", engRaceTextService.getAllEngRaceTexts());
        return "admin/Race/raceList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createRace(MultipartHttpServletRequest request, ModelMap model, @RequestParam("ruName") String ruName,
                             @RequestParam("ruDescription") String ruDescription,
                             @RequestParam("engName") String engName,
                             @RequestParam("engDescription") String engDescription) {
        RaceService raceService = new RaceService();
        FileManager fileManager = new FileManager();
        String ruPicture = fileManager.upload(request.getFile("ruPicture"), context);
        String engPicture = fileManager.upload(request.getFile("engPicture"), context);
        raceService.createRace(ruName, ruDescription, ruPicture, engName, engDescription, engPicture);
        return "redirect:/admin/race/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editRace(ModelMap model, @PathVariable("id") Integer id) {
        EngRaceTextService engRaceTextService = new EngRaceTextService();
        EngRaceText engRaceText = engRaceTextService.getTextsByRaceId(id);
        RuRaceTextService ruRaceTextService = new RuRaceTextService();
        RuRaceText ruRaceText = ruRaceTextService.getTextsByRaceId(id);
        model.addAttribute("ruText", ruRaceText);
        model.addAttribute("engText", engRaceText);
        model.addAttribute("raceId", id);
        return "admin/Race/editRace";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editRace(MultipartHttpServletRequest request, ModelMap model, @RequestParam("ruName") String ruName,
                           @RequestParam("ruDescription") String ruDescription,
                           @RequestParam("engName") String engName,
                           @RequestParam("engDescription") String engDescription,
                           @PathVariable("id") Integer id) {
        RaceService raceService = new RaceService();
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

        raceService.editRace(id, ruName, ruDescription, ruPicture, engName, engDescription, engPicture);
        return "redirect:/admin/race/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteRace(ModelMap model, @PathVariable("id") Integer id) {
        RaceService raceService = new RaceService();
        raceService.deleteRace(id);
        return "redirect:/admin/race/list";
    }

    @RequestMapping(value = "/picture/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        EngRaceTextService engRaceTextService = new EngRaceTextService();
        model.addAttribute("races", engRaceTextService.getAllEngRaceTexts());
        return "admin/Race/Picture/raceList";
    }

    @RequestMapping(value = "/picture/edit/{id}", method = RequestMethod.GET)
    public String createRacePicture(ModelMap model, @PathVariable("id") Integer id) {
        RacePictureService racePictureService = new RacePictureService();
        model.addAttribute("pictures", racePictureService.getRacePicturesByRaceId(id));
        model.addAttribute("raceId", id);
        return "admin/Race/Picture/createRacePicture";
    }

    @RequestMapping(value = "/picture/edit/{id}", method = RequestMethod.POST)
    public String createClass(MultipartHttpServletRequest request, ModelMap model,
                              @PathVariable("id") Integer id, @RequestParam("sex") String sex) {
        boolean isMale = true;
        if (!sex.equalsIgnoreCase("male")) {
            isMale = false;
        }
        RacePictureService racePictureService = new RacePictureService();
        racePictureService.addPictures(request, isMale, context, id);
        return "redirect:/admin/race/picture/edit/" + id;
    }

    @RequestMapping(value = "/picture/changeSex/{racePictureId}", method = RequestMethod.GET)
    public String setRacePictureSex(ModelMap model, @PathVariable("racePictureId") Integer racePictureId) {
        RacePictureService racePictureService = new RacePictureService();
        int raceId = racePictureService.setNewSex(racePictureId);
        return "redirect:/admin/race/picture/edit/" + raceId;
    }

    @RequestMapping(value = "/picture/delete/{racePictureId}", method = RequestMethod.GET)
    public String deleteRacePicture(ModelMap model, @PathVariable("racePictureId") Integer racePictureId) {
        RacePictureService racePictureService = new RacePictureService();
        Integer raceId = racePictureService.getRaceIdByRacePictureId(racePictureId);
        racePictureService.deleteRacePictureById(racePictureId);
        return "redirect:/admin/race/picture/edit/" + raceId;
    }
}
