package com.worldofzaar.service;

import com.worldofzaar.adapter.SupportCardAdapter;
import com.worldofzaar.dao.SupportCardDao;
import com.worldofzaar.entity.Classification;
import com.worldofzaar.entity.Property;
import com.worldofzaar.entity.Subset;
import com.worldofzaar.entity.SupportCard;
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
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class SupportCardService {

    public List<SupportCardAdapter> getCompositeSupportCards(String lang) {
        SupportCardDao supportCardDao = new SupportCardDao();
        List<Object[]> supportCards = supportCardDao.getCompositeSupportCards(lang);
        List<SupportCardAdapter> supportCardAdapter = new ArrayList<SupportCardAdapter>();
        for (Object[] tmp : supportCards) {
            supportCardAdapter.add(new SupportCardAdapter(tmp));
        }
        return supportCardAdapter;
    }

    public void addCard(MultipartHttpServletRequest request, ServletContext context, Integer energy, Integer classId,
                        Integer propertyId, Integer subsetId, String propertyString, String ruName, String engName,
                        String ruSlogan, String engSlogan, Boolean isElite) {
        EngCardTextService engCardTextService = new EngCardTextService();
        RuCardTextService ruCardTextService = new RuCardTextService();
        PropertyService propertyService = new PropertyService();
        ClassificationService classificationService = new ClassificationService();
        SupportCardDao supportCardDao = new SupportCardDao();
        SupportCard supportCard = new SupportCard();
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

        supportCard.setElite(isElite);
        supportCard.setCardEnergy(energy);
        supportCard.setCardPicture(filePath);
        supportCard.setSubset(subset);
        supportCard.setPropertySystemString(propertyString);
        supportCard.setProperty(property);
        supportCard.setClassification(classification);
        supportCardDao.add(supportCard);

        engCardTextService.addText(supportCard, engName, engSlogan);
        ruCardTextService.addText(supportCard, ruName, ruSlogan);
    }

    public void editCard(Integer cardId, MultipartHttpServletRequest request, ServletContext context, Integer energy, Integer classId,
                         Integer propertyId, Integer subsetId, String propertyString, String ruName, String engName,
                         String ruSlogan, String engSlogan, Boolean isElite) {
        EngCardTextService engCardTextService = new EngCardTextService();
        RuCardTextService ruCardTextService = new RuCardTextService();
        PropertyService propertyService = new PropertyService();
        ClassificationService classificationService = new ClassificationService();
        SupportCardDao supportCardDao = new SupportCardDao();
        SupportCard supportCard = new SupportCard();
        SubsetService subsetService = new SubsetService();
        supportCard = supportCardDao.find(cardId);

        Subset subset = subsetService.getSubset(subsetId);
        Property property = propertyService.getProperty(propertyId);
        Classification classification = classificationService.getClassById(classId);


        Iterator<String> iter = request.getFileNames();
        FileManager fileManager = new FileManager();
        String filePath = "";
        while (iter.hasNext()) {
            String fileName = iter.next();
            MultipartFile multipartFile = request.getFile(fileName);
            if (multipartFile.getSize() != 0l)
                //Upload file and get it path.
                filePath = fileManager.upload(multipartFile, context);
        }

        supportCard.setElite(isElite);
        supportCard.setCardEnergy(energy);
        if (!filePath.equals(""))
            supportCard.setCardPicture(filePath);
        supportCard.setSubset(subset);
        supportCard.setPropertySystemString(propertyString);
        supportCard.setProperty(property);
        supportCard.setClassification(classification);
        supportCardDao.update(supportCard);

        engCardTextService.editText(supportCard, engName, engSlogan);
        ruCardTextService.editText(supportCard, ruName, ruSlogan);
    }

    public void deleteCard(Integer cardId) {
        SupportCardDao supportCardDao = new SupportCardDao();
        supportCardDao.remove(cardId);
    }

    public SupportCard getCard(Integer cardId) {
        SupportCardDao supportCardDao = new SupportCardDao();
        return supportCardDao.find(cardId);
    }

    public List<SupportCard> getList() {
        SupportCardDao supportCardDao = new SupportCardDao();
        return supportCardDao.list();
    }
}
