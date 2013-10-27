package com.worldofzaar.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 27.10.13
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */
public class FileUpload {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
