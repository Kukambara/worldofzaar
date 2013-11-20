package com.worldofzaar.service;

import com.worldofzaar.dao.SubsetDao;
import com.worldofzaar.entity.Set;
import com.worldofzaar.entity.Subset;
import com.worldofzaar.util.FileManager;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 14.11.13
 * Time: 12:24
 * To change this template use File | Settings | File Templates.
 */
public class SubsetService {

    public void editSubset(MultipartHttpServletRequest request, ServletContext context,
                           Integer subsetId, Integer setId, String techName) {
        Iterator<String> iter = request.getFileNames();
        FileManager fileManager = new FileManager();
        SetService setService = new SetService();
        Set set = setService.getSetById(setId);
        SubsetDao subsetDao = new SubsetDao();
        Subset subset = subsetDao.find(subsetId);
        while (iter.hasNext()) {
            String fileName = iter.next();
            String filePath = "";
            MultipartFile multipartFile = request.getFile(fileName);
            if (multipartFile.getSize() != 0l) {
                //Upload file and get it path.
                filePath = fileManager.upload(multipartFile, context);
            }

            subset.setSet(set);
            if (!filePath.equals(""))
                subset.setFrontPath(filePath);
            subset.setSubsetTechName(techName);
            subsetDao.update(subset);
        }
    }

    public List<Subset> getList() {
        SubsetDao subsetDao = new SubsetDao();
        return subsetDao.list();

    }

    public void addSubset(MultipartHttpServletRequest request, ServletContext context, Integer setId, String techName) {
        Iterator<String> iter = request.getFileNames();
        FileManager fileManager = new FileManager();
        SubsetDao subsetDao = new SubsetDao();
        SetService setService = new SetService();
        Set set = setService.getSetById(setId);
        while (iter.hasNext()) {
            String fileName = iter.next();
            MultipartFile multipartFile = request.getFile(fileName);
            //Upload file and get it path.
            String filePath = fileManager.upload(multipartFile, context);
            //Create subset.
            Subset subset = new Subset();
            subset.setSet(set);
            subset.setFrontPath(filePath);
            subset.setSubsetTechName(techName);
            subsetDao.add(subset);
        }
    }

    public Subset getSubset(Integer subsetId) {
        SubsetDao subsetDao = new SubsetDao();
        return subsetDao.find(subsetId);
    }

    public void deleteSubset(Integer subsetId) {
        SubsetDao subsetDao = new SubsetDao();
        subsetDao.deleteSubset(subsetId);
    }
}
