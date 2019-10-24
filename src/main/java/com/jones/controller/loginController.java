package com.jones.controller;

import com.jones.model.User;
import com.jones.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * LoginController
 *
 * @author JoNeS
 * @date
 */
@Controller
public class loginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/login.html","/"},method = RequestMethod.GET)
    public String login(){

        return "login";
    }

    @RequestMapping(value = "/login.html",method = RequestMethod.POST)
    public String loginPost(User user,Model model){
        user.setUsername(user.getUsername().trim());
        //1.认证的核心组件:获取subject对象
        Subject subject = SecurityUtils.getSubject();
        //2.将提交过来的用户名和密码封装到token对象
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        //3.调用subject的login方法进行验证
        try {
            //token传递到了com.jones.shiro.ShiroRealm里de supports方法中了
            subject.login(token);
            if("admin".equals(token.getUsername())){
                return "redirect:/admin/admin.html";
            }else{
                return "redirect:/index.html";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "loginError";
        }
    }
}
