package cn.edu.zime.iot.zzz_11.controller;

import cn.edu.zime.iot.zzz_11.Model.User;
import cn.edu.zime.iot.zzz_11.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller

public class LoginController
{
    @Autowired
    private UserDao usersDAO;

    @GetMapping({"/","/index.html"})
    public String index(){
        return "login";
    }

    @PostMapping("/login")

    public String login(String userName, String password, Map<String, Object> map, HttpServletRequest request) {

        User u = new User();
        u.setUsername(userName);
        u.setPassword(password);
        System.out.println("get");
        if (usersDAO.verify(u)) {
            System.out.println("done");
            request.getSession().setAttribute("login",true);
            return "control";
        } else {
            map.put("msg","用户名或密码错误！");
            return "login";
        }

    }
}