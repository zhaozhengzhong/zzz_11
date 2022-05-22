package cn.edu.zime.iot.zzz_11.controller;

import cn.edu.zime.iot.zzz_11.Model.Password;
import cn.edu.zime.iot.zzz_11.Model.User;
import cn.edu.zime.iot.zzz_11.config.GlobalConfig;
import cn.edu.zime.iot.zzz_11.dao.UserDao;
import cn.edu.zime.iot.zzz_11.service.UserService;
import cn.edu.zime.iot.zzz_11.util.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody

public class UserController {
    @Autowired
    GlobalConfig globalConfig;
    @Autowired
    private UserService userService;
    private UserDao userDao;
    @GetMapping("/AllUser")
    public GeneralResponse users()
    {
        Password p=new Password();
        GeneralResponse response =new GeneralResponse();
        response.setSuccess(true);
        List<User> result=new ArrayList<>();
        List<User> users=new ArrayList<>();
        result=userService.getAllUser();
        response.setData(result);
        return response;
    }

    @PostMapping("/user/add")
    public GeneralResponse add(@RequestParam String username, @RequestParam String password, @RequestParam int power)
    {
        GeneralResponse response = new GeneralResponse();
        Password p=new Password();
        response.setSuccess(false);
        if(globalConfig.getPower()==1)
        {
            User user = new User();
            user.setUsername(username);
            p.setPassword(password);
            user.setPassword(p.getPassword());
            user.setPower(power);
            response.setSuccess(true);
            response.setData(userService.addUser(user));
        }


        return response;
    }

    @PutMapping("/user/update")
    public GeneralResponse update(@RequestParam String username,@RequestParam String password,@RequestParam int power,@RequestParam int id)
    {
        GeneralResponse response =new GeneralResponse();
        response.setSuccess(false);
        Password p=new Password();
        System.out.println("power:"+globalConfig.getPower());
        if(globalConfig.getPower()==1)
        {
            User user = new User();
            user.setUsername(username);
            user.setId(id);
            user.setPower(power);
            user.setPassword(password);
            response.setSuccess(true);
            response.setData(userService.updateUser(user));
        }

        return response;
    }

    @PostMapping("/user/delete")
    public GeneralResponse delete(@RequestParam int id)
    {
        GeneralResponse response =new GeneralResponse();
        response.setSuccess(false);
        if(globalConfig.getPower()==1)
        {
            response.setSuccess(true);
            response.setData(userService.deleteUserById(id));
        }

        return response;
    }

    @GetMapping("/user")
    public GeneralResponse userById(@RequestParam int id)
    {
        GeneralResponse response =new GeneralResponse();
        response.setSuccess(true);
        List<User>result=new ArrayList<>();
        result.add(userService.getUserById(id));
        response.setData(result);
        System.out.println(result.toString());
        return response;
    }
}