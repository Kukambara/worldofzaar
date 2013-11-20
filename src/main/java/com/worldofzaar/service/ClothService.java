package com.worldofzaar.service;

import com.worldofzaar.dao.ClothDao;
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
 * Date: 14.10.13
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public class ClothService {
    public List<Cloth> getAllClothes() {
        ClothDao clothDao = new ClothDao();
        return clothDao.list();
    }

    public void deleteCloth(Integer clothId) {
        ClothDao clothDao = new ClothDao();
        clothDao.deleteCloth(clothId);
    }

    public void addClothes(MultipartHttpServletRequest request, ServletContext context) {
        Iterator<String> iter = request.getFileNames();
        FileManager fileManager = new FileManager();
        ClothDao clothDao = new ClothDao();
        while (iter.hasNext()) {
            String fileName = iter.next();
            MultipartFile multipartFile = request.getFile(fileName);
            //Upload file and get it path.
            String filePath = fileManager.upload(multipartFile, context);
            //Create racePicture.
            Cloth cloth = new Cloth();
            cloth.setClothPath(filePath);
            clothDao.add(cloth);
        }
    }

    public void editCloth(MultipartHttpServletRequest request, ServletContext context, Integer clothId) {
        Iterator<String> iter = request.getFileNames();
        FileManager fileManager = new FileManager();
        ClothDao clothDao = new ClothDao();
        while (iter.hasNext()) {
            String fileName = iter.next();
            MultipartFile multipartFile = request.getFile(fileName);
            //Upload file and get it path.
            String filePath = fileManager.upload(multipartFile, context);
            //Create racePicture.
            Cloth cloth = clothDao.find(clothId);
            cloth.setClothPath(filePath);
            clothDao.update(cloth);
        }
    }

    public Cloth getCloth(Integer id) {
        ClothDao clothDao = new ClothDao();
        return clothDao.find(id);
    }

}
