package com.worldofzaar.service;

import com.worldofzaar.dao.WebUserDao;
import com.worldofzaar.entity.WebUser;
import com.worldofzaar.util.EmailSender;
import com.worldofzaar.util.HashConverter;
import com.worldofzaar.util.WOZConsts;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 13.10.13
 * Time: 17:35
 * To change this template use File | Settings | File Templates.
 */
public class AuthorizationService {

    //Add webUser to admin table for accepting permission.
    AdminService adminService = new AdminService();

    public boolean login(String email, String pass, HttpServletRequest request) {
        WebUserDao dao = new WebUserDao();
        WebUser webUser;
        webUser = dao.signInEmail(email, pass);

        if (webUser != null) {
           /*
           // Раскоментировать если нужно ограничение на подтвержденного пользователя.
            if (!webUser.getApproved())
                return false;
             */

            boolean isAdmin = new AdminService().isAdmin(webUser);
            if (webUser.getUser() != null)
                request.getSession().setAttribute(WOZConsts.USER_ID, webUser.getUser().getUserId());
            request.getSession().setAttribute(WOZConsts.USER_EMAIL, webUser.getWebUserEmail());
            request.getSession().setAttribute(WOZConsts.IS_ADMIN, isAdmin);

            return true;
        }
        return false;
    }

    public void logout(HttpServletRequest request) {
        request.getSession().removeAttribute(WOZConsts.USER_ID);
        request.getSession().removeAttribute(WOZConsts.IS_ADMIN);

    }

    public boolean registration(HttpServletRequest request, String email, String pass) {

        WebUser webUser = new WebUser();
        webUser.setApproved(false);
        webUser.setWebUserPass(pass);
        webUser.setWebUserEmail(email);

        WebUserDao dao = new WebUserDao();

        //Search existing user.
        WebUser existingWebUser = dao.getWebUserByEmail(email);
        if (existingWebUser != null) {
            return false;
        }

        dao.add(webUser);

        //Send email to confirm account.
        StringBuilder emailBody = new StringBuilder("Thanks for registration at \"World of Zaar\".")
                .append(" Select the link below to confirm your registration.\n\r\n\r")
                .append(WOZConsts.SITE_URL).append("confirm/").append(webUser.getWebUserEmail()).append("/")
                .append(HashConverter.md5(webUser.getWebUserEmail() + webUser.getWebUserPass()));
        EmailSender emailSender = new EmailSender();
        emailSender.send("Confirm Your account \"World of Zaar\"", emailBody.toString(), webUser.getWebUserEmail());

        return true;
    }

    public boolean registrationAdmin(HttpServletRequest request, String email, String pass) {

        WebUser webUser = new WebUser();
        webUser.setApproved(false);
        webUser.setWebUserPass(pass);
        webUser.setWebUserEmail(email);

        WebUserDao dao = new WebUserDao();

        //Search existing user.
        WebUser existingWebUser = dao.getWebUserByEmail(email);
        if (existingWebUser != null) {
            return false;
        }


        dao.add(webUser);

        //Send email to confirm account.
        StringBuilder emailBody = new StringBuilder("Thanks for registration at \"World of Zaar\".")
                .append(" Select the link below to confirm your registration.\n\r\n\r")
                .append(WOZConsts.SITE_URL).append("confirm/").append(webUser.getWebUserEmail()).append("/")
                .append(HashConverter.md5(webUser.getWebUserEmail() + webUser.getWebUserPass()));
        EmailSender emailSender = new EmailSender();
        emailSender.send("Confirm Your account \"World of Zaar\"", emailBody.toString(), webUser.getWebUserEmail());

        return true;
    }

    public boolean restoreAccount(String email) {
        WebUserDao dao = new WebUserDao();
        WebUser webUser = dao.getWebUserByEmail(email);
        if (webUser != null) {
            StringBuilder emailBody = new StringBuilder("Restoring account at \"World of Zaar\".\n\r")
                    .append("\n\rPassword: ").append(webUser.getWebUserPass());
            EmailSender emailSender = new EmailSender();
            emailSender.send("Restore account \"World of Zaar\"", emailBody.toString(), webUser.getWebUserEmail());
            return true;
        } else
            return false;
    }

    public boolean confirmAccount(String email, String md5, HttpServletRequest request) {
        WebUserDao dao = new WebUserDao();
        WebUser webUser = dao.getWebUserByEmail(email);
        if (webUser != null) {
            if (HashConverter.md5(webUser.getWebUserEmail() + webUser.getWebUserPass()).equalsIgnoreCase(md5)) {
                webUser.setApproved(true);
                dao.update(webUser);
                login(webUser.getWebUserEmail(), webUser.getWebUserPass(), request);
                return true;
            }
        }
        return false;
    }

    public boolean setAdminAccount(String email, String md5, HttpServletRequest request) {
        WebUserDao dao = new WebUserDao();
        WebUser webUser = dao.getWebUserByEmail(email);
        if (webUser != null) {
            if (HashConverter.md5(webUser.getWebUserEmail() + webUser.getWebUserPass()).equalsIgnoreCase(md5)) {
                AdminService serv = new AdminService();
                webUser.setApproved(true);
                serv.addWebUser(webUser);
                login(webUser.getWebUserEmail(), webUser.getWebUserPass(), request);
                return true;
            }
        }
        return false;
    }
}
