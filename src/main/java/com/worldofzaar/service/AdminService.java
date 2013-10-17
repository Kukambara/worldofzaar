package com.worldofzaar.service;

import com.worldofzaar.dao.AdminDao;
import com.worldofzaar.entity.Admin;
import com.worldofzaar.entity.WebUser;
import com.worldofzaar.util.WOZConsts;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 13.10.13
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */
public class AdminService {

    public void signIn(String email, String password) {
        AdminDao dao = new AdminDao();
    }

    public boolean isAdmin(WebUser webUser) {
        AdminDao dao = new AdminDao();
        Admin admin = dao.getAdminByWebUser(webUser);
        if (admin != null)
            return admin.getApproved(); // Вернет true, если пользователь есть в таблице админы и он подтвержден.
        return false;
    }

    public void addWebUser(WebUser webUser) {
        AdminDao adminDao = new AdminDao();
        Admin admin = new Admin();
        admin.setWebUser(webUser);
        if (webUser.getWebUserEmail().equals(WOZConsts.NOREPLY_WORLDOFZAAR_EMAIL))
            admin.setApproved(true);
        else
            admin.setApproved(false);
        adminDao.add(admin);
    }

    public List<Admin> getAdminList() {
        AdminDao adminDao = new AdminDao();
        return adminDao.list();
    }

    public void deleteAdmin(Integer id) {
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.find(id);
        if (admin != null) {
            adminDao.remove(admin);
        }

    }

    public void approveAdmin(Integer id) {
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.find(id);
        if (admin != null) {
            admin.setApproved(true);
            adminDao.update(admin);
        }
    }

    public void disapproveAdmin(Integer id) {
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.find(id);
        if (admin != null) {
            admin.setApproved(false);
            adminDao.update(admin);
        }
    }
}
