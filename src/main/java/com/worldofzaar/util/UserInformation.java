package com.worldofzaar.util;

import com.worldofzaar.entity.User;
import com.worldofzaar.service.UserService;

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
    private User user;


    public UserInformation(HttpServletRequest request) {
        this.isAdmin = (Boolean) request.getSession().getAttribute(WOZConsts.IS_ADMIN);
        this.userId = (Integer) request.getSession().getAttribute(WOZConsts.USER_ID);
        this.email = (String) request.getSession().getAttribute(WOZConsts.USER_EMAIL);
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

    public User getUser() {
        if (user == null) {
            UserService userService = new UserService();
            user = userService.getUser(userId);
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        userId = user.getUserId();
    }
}
