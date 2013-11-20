package com.worldofzaar.service;

import com.worldofzaar.dao.PropertyDao;
import com.worldofzaar.entity.Property;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
public class PropertyService {
    public List<Property> getAllProperties() {
        PropertyDao propertyDao = new PropertyDao();
        return propertyDao.list();
    }

    public void addProperty(String realization, String description) {
        PropertyDao propertyDao = new PropertyDao();
        Property property = new Property();
        property.setPropertyRealization(realization);
        property.setPropertySystemDescription(description);
        propertyDao.add(property);

    }

    public void updateProperty(Integer id, String realization, String description) {
        PropertyDao propertyDao = new PropertyDao();
        Property property = propertyDao.find(id);

        // If property equals null then stop updating;
        if (property == null) return;

        property.setPropertyRealization(realization);
        property.setPropertySystemDescription(description);

        propertyDao.update(property);

    }

    public Property getProperty(Integer id) {
        PropertyDao propertyDao = new PropertyDao();
        return propertyDao.find(id);
    }

    public void deleteProperty(Integer propertyId) {
        PropertyDao propertyDao = new PropertyDao();
        propertyDao.remove(propertyId);
    }
}
