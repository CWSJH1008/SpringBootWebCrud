package com.gd.test.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2020/1/10.
 */
@Controller
public class LoginController {

    @PostMapping(value="/user/login")
    //@RequestMapping(value="/user/login",method= RequestMethod.POST)
    public String login(@RequestParam(value ="username",required = false) String username,
                        @RequestParam(value = "password",required = false) String password,
                        Map<String,Object> map, HttpSession session){
            if(!StringUtils.isEmpty(username) && "123".equals(password)) {
                session.setAttribute("loginUser",username);
                return "redirect:/main.html";
            } else{
                map.put("msg","用户名密码错误");
                return "index";
            }

    }
    @GetMapping(value = "/user/logout")
    public void loginOut(HttpSession session, HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        session.removeAttribute("username");
        session.invalidate();
        request.getRequestDispatcher("/index.html").forward(request,response);
        //return "redirect:/index.html";
    }
}
