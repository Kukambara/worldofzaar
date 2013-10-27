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
    public void createRuClassText(Classification classification, String ruName, String ruDescription) {
        RuClassTextDao ruClassTextDao = new RuClassTextDao();
        RuClassText ruClassText = new RuClassText();
        ruClassText.setClassification(classification);
        ruClassText.setClassName(ruName);
        ruClassText.setClassDescription(ruDescription);
        ruClassTextDao.add(ruClassText);
    }
}
