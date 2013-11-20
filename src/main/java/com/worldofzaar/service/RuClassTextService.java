package com.worldofzaar.service;

import com.worldofzaar.dao.RuClassTextDao;
import com.worldofzaar.entity.Classification;
import com.worldofzaar.entity.RuClassText;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 18:02
 * To change this template use File | Settings | File Templates.
 */
public class RuClassTextService {
    public void createText(Classification classification, String ruName, String ruDescription, String ruPicture) {
        RuClassTextDao ruClassTextDao = new RuClassTextDao();
        RuClassText ruClassText = new RuClassText();
        ruClassText.setClassification(classification);
        ruClassText.setClassName(ruName);
        ruClassText.setClassDescription(ruDescription);
        ruClassText.setClassPictureNamePath(ruPicture);
        ruClassTextDao.add(ruClassText);
    }

    public void editText(Classification classification, String ruName, String ruDescription, String ruPicture) {
        RuClassTextDao ruClassTextDao = new RuClassTextDao();
        RuClassText ruClassText = ruClassTextDao.getTextByClassId(classification.getClassificationId());
        ruClassText.setClassName(ruName);
        ruClassText.setClassDescription(ruDescription);
        if (!ruPicture.equals(""))
            ruClassText.setClassPictureNamePath(ruPicture);
        ruClassTextDao.update(ruClassText);
    }

    public RuClassText getTextByClassId(Integer classId) {
        RuClassTextDao ruClassTextDao = new RuClassTextDao();
        return ruClassTextDao.getTextByClassId(classId);
    }

    public void deleteText(Integer classId) {
        RuClassTextDao ruClassTextDao = new RuClassTextDao();
        ruClassTextDao.deleteText(classId);
    }
}
