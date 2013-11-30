package com.worldofzaar.interceptors;

import com.worldofzaar.service.AuthorizationService;
import com.worldofzaar.util.WOZConsts;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 14.07.13
 * Time: 20:43
 * To change this template use File | Settings | File Templates.
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(WOZConsts.IS_ADMIN) != null)
            return true;
        else {
            if (loginByCookies(request, response)) {
                response.sendRedirect("/profile");
            } else {
                response.sendRedirect("/");
            }
            return false;
        }
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
