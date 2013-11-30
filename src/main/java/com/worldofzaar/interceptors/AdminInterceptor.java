package com.worldofzaar.interceptors;

import com.worldofzaar.service.AuthorizationService;
import com.worldofzaar.util.WOZConsts;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute(WOZConsts.IS_ADMIN) == null || (Boolean) request.getSession().getAttribute(WOZConsts.IS_ADMIN) != true) {
            if (loginByCookies(request, response)) {
                response.sendRedirect("/admin");
            } else {
                response.sendRedirect("/adminSignIn");
            }
            return false;
        }
        return true;
    }

    private boolean loginByCookies(HttpServletRequest request, HttpServletResponse response) {
        AuthorizationService authorizationService = new AuthorizationService();
        return authorizationService.loginByCookies(request, response);
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
