package com.worldofzaar.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 27.10.13
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */
public class FileManager {
    public static String PATH = "/resources/pictures/";

    public String upload(MultipartFile mpf, ServletContext context) {
        String realPath = context.getRealPath("") + FileManager.PATH;
        File theDir = new File(realPath);
        String filePath = realPath + getHashFileName(mpf.getOriginalFilename());
        String pathToReturn = FileManager.PATH + getHashFileName(mpf.getOriginalFilename());
        try {

            // if the directory does not exist, create it
            if (!theDir.exists()) theDir.mkdir();
            // copy file to local disk
            FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pathToReturn;
    }

    public boolean delete(String path, ServletContext context) {
        String realPath = context.getRealPath("");
        try {
            File file = new File(realPath + path);
            return file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getHashFileName(String fileName) {
        String extension = ".";
        int pointPosition = fileName.lastIndexOf('.');
        if (pointPosition > 0) {
            extension += fileName.substring(pointPosition + 1);
        }
        fileName = HashConverter.md5(fileName);
        fileName += extension;
        return fileName;

    }
}