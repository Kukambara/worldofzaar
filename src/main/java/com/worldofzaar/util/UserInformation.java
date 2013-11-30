package com.worldofzaar.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 30.11.13
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class UserInformation {
    private Boolean isAdmin;
    private String email;
    private Integer userId;


    public UserInformation(HttpServletRequest request) {
        request.getSession().getAttribute(WOZConsts.IS_ADMIN);
        request.getSession().getAttribute(WOZConsts.USER_ID);
        request.getSession().getAttribute(WOZConsts.USER_EMAIL);
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
