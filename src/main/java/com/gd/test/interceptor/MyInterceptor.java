package com.gd.test.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2020/1/11.
 */

public class MyInterceptor implements HandlerInterceptor {

    private Object username;
    private HttpSession session;

    //在目标方法执行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        username=request.getSession().getAttribute("loginUser");

       // System.out.println("登录姓名是："+username);
        if(username == null){
            request.setAttribute("msg","没有权限，请先登录");
            System.out.println("登录未通过");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            //已登录 放行
           // System.out.println("判断了已登录了");
            return true;
        }


    }
    //在目标方法处理之后，视图渲染之前调用
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //System.out.println("视图渲染之前，调用之后");

    }
    //在目标方法且视图渲染之后调用
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        //System.out.println("视图渲染之后，调用之后");
    }
}
