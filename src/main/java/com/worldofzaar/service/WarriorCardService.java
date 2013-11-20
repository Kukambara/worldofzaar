package com.worldofzaar.service;

import com.worldofzaar.adapter.WarriorCardAdapter;
import com.worldofzaar.dao.WarriorCardDao;
import com.worldofzaar.entity.Classification;
import com.worldofzaar.entity.Property;
import com.worldofzaar.entity.Subset;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.FileManager;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */
public class WarriorCardService {

    public List<WarriorCardAdapter> getCompositeWarriorsCards(String lang) {
        WarriorCardDao warriorCardDao = new WarriorCardDao();
        List<Object[]> warriorCards = warriorCardDao.getCompositeWarriorsCards(lang);

        List<WarriorCardAdapter> warriorCardAdapter = new ArrayList<WarriorCardAdapter>();
        for (Object[] tmp : warriorCards) {
            warriorCardAdapter.add(new WarriorCardAdapter(tmp));
        }
        return warriorCardAdapter;
    }

    public void addCard(MultipartHttpServletRequest request, ServletContext context, Integer energy, Integer classId,
                        Integer propertyId, Integer subsetId,
                        String propertyString, Integer armor, Integer damage, Integer health,
                        String ruName, String engName, String ruSlogan, String engSlogan, Boolean isElite) {
        EngCardTextService engCardTextService = new EngCardTextService();
        RuCardTextService ruCardTextService = new RuCardTextService();
        PropertyService propertyService = new PropertyService();
        ClassificationService classificationService = new ClassificationService();
        WarriorCardDao warriorCardDao = new WarriorCardDao();
        WarriorCard warriorCard = new WarriorCard();
        SubsetService subsetService = new SubsetService();

        Subset subset = subsetService.getSubset(subsetId);
        Property property = propertyService.getProperty(propertyId);
        Classification classification = classificationService.getClassById(classId);


        Iterator<String> iter = request.getFileNames();
        FileManager fileManager = new FileManager();
        String filePath = "";
        while (iter.hasNext()) {
            String fileName = iter.next();
            MultipartFile multipartFile = request.getFile(fileName);
            //Upload file and get it path.
            filePath = fileManager.upload(multipartFile, context);
        }

        warriorCard.setElite(isElite);
        warriorCard.setCardArmor(armor);
        warriorCard.setCardDamage(damage);
        warriorCard.setCardHealth(health);
        warriorCard.setCardEnergy(energy);
        warriorCard.setCardPicture(filePath);
        warriorCard.setSubset(subset);
        warriorCard.setPropertySystemString(propertyString);
        warriorCard.setProperty(property);
        warriorCard.setClassification(classification);
        warriorCardDao.add(warriorCard);

        engCardTextService.addText(warriorCard, engName, engSlogan);
        ruCardTextService.addText(warriorCard, ruName, ruSlogan);
    }

    public List<WarriorCard> getList() {
        WarriorCardDao warriorCardDao = new WarriorCardDao();
        return warriorCardDao.list();
    }


}
