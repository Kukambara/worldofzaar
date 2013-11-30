package com.worldofzaar.service;

import com.worldofzaar.dao.BlazonDao;
import com.worldofzaar.entity.Blazon;
import com.worldofzaar.entity.Classification;
import com.worldofzaar.entity.Cloth;
import com.worldofzaar.util.FileManager;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:42
 * To change this template use File | Settings | File Templates.
 */
public class BlazonService {
    public List<Blazon> getBlazonsByClothId(Integer clothId) {
        BlazonDao blazonDao = new BlazonDao();
        return blazonDao.getBlazonsByClothId(clothId);
    }

    public void addBlazons(MultipartHttpServletRequest request, ServletContext context, Integer clothId, Integer classId) {
        Iterator<String> iter = request.getFileNames();
        FileManager fileManager = new FileManager();
        BlazonDao blazonDao = new BlazonDao();
        ClothService clothService = new ClothService();
        Cloth cloth = clothService.getCloth(clothId);
        ClassificationService classificationService = new ClassificationService();
        Classification classification = classificationService.getClassById(classId);
        while (iter.hasNext()) {
            String fileName = iter.next();
            MultipartFile multipartFile = request.getFile(fileName);
            //Upload file and get it path.
            String filePath = fileManager.upload(multipartFile, context);
            //Create racePicture.
            Blazon blazon = new Blazon();
            blazon.setBlazonPath(filePath);
            blazon.setClassification(classification);
            blazon.setCloth(cloth);
            blazonDao.add(blazon);
        }
    }

    public Integer changeClass(MultipartHttpServletRequest request, ServletContext context, Integer blazonId, Integer classId) {
        BlazonDao blazonDao = new BlazonDao();
        Blazon blazon = blazonDao.find(blazonId);
        ClassificationService classificationService = new ClassificationService();
        Classification classification = classificationService.getClassById(classId);
        Iterator<String> iter = request.getFileNames();
        FileManager fileManager = new FileManager();
        while (iter.hasNext()) {
            String fileName = iter.next();
            MultipartFile multipartFile = request.getFile(fileName);
            //Upload file and get it path.
            String filePath = fileManager.upload(multipartFile, context);
            //Create racePicture.
            blazon.setBlazonPath(filePath);
            blazon.setClassification(classification);
            blazonDao.update(blazon);
        }
        return blazon.getCloth().getClothId();
    }

    public void deleteBlazon(Integer blazonId) {
        BlazonDao blazonDao = new BlazonDao();
        blazonDao.deleteBlazon(blazonId);
    }

    public Integer getClothIdByBlazonId(Integer blazonId) {
        BlazonDao blazonDao = new BlazonDao();
        return blazonDao.getClothIdByBlazonId(blazonId);
    }

    public Blazon getBlazon(Integer blazonId) {
        BlazonDao blazonDao = new BlazonDao();
        return blazonDao.find(blazonId);
    }
}
