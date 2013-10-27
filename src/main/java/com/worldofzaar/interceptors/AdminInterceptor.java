package com.worldofzaar.interceptors;

import com.worldofzaar.service.AuthorizationService;
import com.worldofzaar.util.WOZConsts;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute(WOZConsts.IS_ADMIN) == null || (Boolean) request.getSession().getAttribute(WOZConsts.IS_ADMIN) != true) {
            if (canLoginByCookies(request, response)) {
                response.sendRedirect("/admin");
            } else
                response.sendRedirect("/adminSignIn");
            return false;
        }
        return true;
    }

    private boolean canLoginByCookies(HttpServletRequest request, HttpServletResponse response) {
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
            AuthorizationService authorizationService = new AuthorizationService();
            return authorizationService.loginWithHashedPass(email, password, request, response);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
