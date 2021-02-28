package com.lala.yanglao.web.admin;

import com.lala.yanglao.dao.UserDao;
import com.lala.yanglao.model.User;
import com.lala.yanglao.service.UserService;
import com.lala.yanglao.unitl.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/userLogin")
    public String userLogin(String username, String password, HttpSession session, RedirectAttributes attributes){
        User user = userService.checkUser(username);//加密
        if (user!=null&&user.getPassword().equals(MD5Utils.code(password))) {
            session.setAttribute("user", username);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/login";//重定向请求，避免在原基础网址上进行字符串拼接所引起的访问失败(model不适用与重定向请求，会获取不到)
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }
}
