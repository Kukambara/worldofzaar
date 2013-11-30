package com.worldofzaar.service;

import com.worldofzaar.dao.WebUserDao;
import com.worldofzaar.entity.WebUser;
import com.worldofzaar.util.EmailSender;
import com.worldofzaar.util.HashConverter;
import com.worldofzaar.util.PasswordGenerator;
import com.worldofzaar.util.WOZConsts;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private int cookieAge = 60 * 60 * 24 * 7; // Cookie age = 1 week.

    public boolean login(String email, String pass, HttpServletRequest request, HttpServletResponse response) {

        //Hashing pass.
        pass = HashConverter.md5(pass);

        WebUserDao dao = new WebUserDao();
        WebUser webUser;
        webUser = dao.signInEmail(email, pass);
        return login(webUser, response, request);
    }

    public boolean loginWithHashedPass(String email, String pass, HttpServletRequest request, HttpServletResponse response) {

        WebUserDao dao = new WebUserDao();
        WebUser webUser;
        webUser = dao.signInEmail(email, pass);
        return login(webUser, response, request);
    }

    private boolean login(WebUser webUser, HttpServletResponse response, HttpServletRequest request) {
        if (webUser != null) {
           /*
           // Раскоментировать если нужно ограничение на подтвержденного пользователя.
            if (!webUser.getApproved())
                return false;
             */

            //Write user data to Cookie
            Cookie cookieEmail = new Cookie(WOZConsts.USER_EMAIL, webUser.getWebUserEmail());
            cookieEmail.setMaxAge(cookieAge);
            Cookie cookiePass = new Cookie(WOZConsts.USER_PASSWORD, webUser.getWebUserPass());
            cookiePass.setMaxAge(cookieAge);
            Cookie cookieWebUserId = new Cookie(WOZConsts.WEBUSER_ID, String.valueOf(webUser.getWebUserId()));
            cookiePass.setMaxAge(cookieAge);
            response.addCookie(cookieEmail);
            response.addCookie(cookiePass);
            response.addCookie(cookieWebUserId);


            //Write user data to Session
            boolean isAdmin = new AdminService().isAdmin(webUser);
            request.getSession().setAttribute(WOZConsts.USER_EMAIL, webUser.getWebUserEmail());
            request.getSession().setAttribute(WOZConsts.IS_ADMIN, isAdmin);
            request.getSession().setAttribute(WOZConsts.WEBUSER_ID, webUser.getWebUserId());

            //Write user id in session and cookie if it's exist.
            if (webUser.getUser() != null) {
                request.getSession().setAttribute(WOZConsts.USER_ID, webUser.getUser().getUserId());

                //Cookie for userId
                Cookie cookieUserId = new Cookie(WOZConsts.USER_ID, String.valueOf(webUser.getUser().getUserId()));
                cookieUserId.setMaxAge(cookieAge);
                response.addCookie(cookieUserId);
            }


            return true;
        }
        return false;

    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(WOZConsts.USER_ID);
        request.getSession().removeAttribute(WOZConsts.USER_EMAIL);
        request.getSession().removeAttribute(WOZConsts.IS_ADMIN);
        request.getSession().removeAttribute(WOZConsts.WEBUSER_ID);

        //Subset cookie time for 0. And Write new cookie with the same names.
        Cookie cookieEmail = new Cookie(WOZConsts.USER_EMAIL, null);
        cookieEmail.setMaxAge(0);
        Cookie cookiePass = new Cookie(WOZConsts.USER_PASSWORD, null);
        cookiePass.setMaxAge(0);
        Cookie cookieUserId = new Cookie(WOZConsts.USER_ID, null);
        cookieUserId.setMaxAge(0);
        Cookie cookieWebUserId = new Cookie(WOZConsts.WEBUSER_ID, null);
        cookieUserId.setMaxAge(0);

        response.addCookie(cookieEmail);
        response.addCookie(cookiePass);
        response.addCookie(cookieUserId);
        response.addCookie(cookieWebUserId);

    }

    public boolean registration(HttpServletRequest request, String email, String pass) {

        //Hashing pass.
        pass = HashConverter.md5(pass);

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

        //Hashing pass.
        pass = HashConverter.md5(pass);

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

        //If web user exist  generate new password and send it to client's email.
        if (webUser != null) {

            String newPassword = PasswordGenerator.generatePassword(webUser.getWebUserEmail());
            //Change password and hash them.
            webUser.setWebUserPass(HashConverter.md5(newPassword));
            dao.update(webUser);

            //Send email.
            StringBuilder emailBody = new StringBuilder("Restoring account at \"World of Zaar\".\n\r")
                    .append("\n\rNew password: ").append(newPassword);
            EmailSender emailSender = new EmailSender();
            emailSender.send("Restore account \"World of Zaar\"", emailBody.toString(), webUser.getWebUserEmail());
            return true;
        } else
            return false;
    }

    public boolean confirmAccount(String email, String md5, HttpServletRequest request, HttpServletResponse response) {
        WebUserDao dao = new WebUserDao();
        WebUser webUser = dao.getWebUserByEmail(email);
        if (webUser != null) {
            if (HashConverter.md5(webUser.getWebUserEmail() + webUser.getWebUserPass()).equalsIgnoreCase(md5)) {
                webUser.setApproved(true);
                dao.update(webUser);
                login(webUser, response, request);
                return true;
            }
        }
        return false;
    }

    public boolean setAdminAccount(String email, String md5, HttpServletRequest request, HttpServletResponse response) {
        WebUserDao dao = new WebUserDao();
        WebUser webUser = dao.getWebUserByEmail(email);
        if (webUser != null) {
            if (HashConverter.md5(webUser.getWebUserEmail() + webUser.getWebUserPass()).equalsIgnoreCase(md5)) {
                AdminService serv = new AdminService();
                webUser.setApproved(true);
                serv.addWebUser(webUser);
                login(webUser, response, request);
                return true;
            }
        }
        return false;
    }

    public boolean loginByCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String email = "";
        String password = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(WOZConsts.USER_EMAIL))
                email = cookie.getValue();
            else if (cookie.getName().equals(WOZConsts.USER_PASSWORD))
                password = cookie.getValue();
        }

        if (!email.equals("") && !password.equals("")) {
            return loginWithHashedPass(email, password, request, response);
        }
        return false;
    }
}
