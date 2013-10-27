package com.worldofzaar.service;

import com.worldofzaar.dao.EngClassTextDao;
import com.worldofzaar.entity.Classification;
import com.worldofzaar.entity.EngClassText;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 23.10.13
 * Time: 18:02
 * To change this template use File | Settings | File Templates.
 */
public class EngClassTextService {
    public List<EngClassText> getAllEngClassTexts() {
        EngClassTextDao engClassTextDao = new EngClassTextDao();
        return engClassTextDao.list();
    }

    public void createEngClassText(Classification classification, String engName, String engDescription) {
        EngClassTextDao engClassTextDao = new EngClassTextDao();
        EngClassText engClassText = new EngClassText();
        engClassText.setClassification(classification);
        engClassText.setClassName(engName);
        engClassText.setClassDescription(engDescription);
        engClassTextDao.add(engClassText);
    }
}
