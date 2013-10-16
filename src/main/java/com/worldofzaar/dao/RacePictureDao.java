package com.worldofzaar.dao;

import com.worldofzaar.entity.RacePicture;
import com.worldofzaar.entity.WarriorCard;
import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 11.10.13
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */
public class RacePictureDao extends GenericDaoMain<RacePicture> {
    public RacePictureDao(){
        super(new RacePicture());
    }

    public List<RacePicture> list() {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            List racePictures = (List) session.createQuery("from RacePicture").list();
            session.close();
            return racePictures;
        } catch (Exception e) {
            System.out.println("list() Error = " + e.getCause());
        }
        return null;
    }
}
