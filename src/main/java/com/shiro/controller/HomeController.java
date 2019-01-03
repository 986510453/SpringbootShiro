package com.shiro.controller;

import com.shiro.bean.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping({"/","/index"})
    public String index(){
        int i = 0;
        if(i<1){
            return "userUpdate";
        }
        return "index";
    }

    @GetMapping({"/login"})
    public String login(HttpSession session){
        UserInfo judgeUser = (UserInfo) session.getAttribute("users");
        if(judgeUser!=null) {
            return "redirect:/index";
        }
        return "login";
    }

    @Autowired
    HttpSession session;

    @PostMapping("/login")
    public String login(HttpServletRequest request, String username, String password,boolean rememberMe, Map<String, Object> map) throws Exception{
        System.out.println("HomeController.login()");
        //如果有点击  记住我
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password,rememberMe);
        //UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        UserInfo user=(UserInfo) subject.getPrincipal();
        //更新用户登录时间，也可以在ShiroRealm里面做
        session.setAttribute("user", user);
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        UserInfo user = (UserInfo)session.getAttribute("users");
        System.out.println("User:"+user);
        if(user!=null) {
            // 移除session
            session.removeAttribute("user");
        }
        return "login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(HttpServletRequest request){
        System.out.println(request.getRequestURI());
        System.out.println("------没有权限-------");
        return "403";
    }

}